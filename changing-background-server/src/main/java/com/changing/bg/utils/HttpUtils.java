package com.changing.bg.utils;

import com.alibaba.fastjson.JSON;
import com.changing.bg.framework.exceptions.HttpFailException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.Charsets;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Slf4j
public class HttpUtils {

    private HttpUtils() {
    }

    /**
     * simple http post method
     *
     * @param url    request url
     * @param params request params
     * @return response result
     * @throws HttpFailException request fail exception
     */
    public static String bodyPost(String url, Object params) throws HttpFailException {

        return bodyPost(url, params, null);
    }

    /**
     * @param url     request url
     * @param params  request params
     * @param headers request headers
     * @return response result
     * @throws HttpFailException request fail exception
     */
    public static String bodyPost(String url, Object params, Map<String, String> headers) throws HttpFailException {

        return bodyPost(url, params, headers, null);
    }

    /**
     * @param url         request url
     * @param params      request params
     * @param headers     request headers
     * @param respCharset response result charset
     * @return response result
     * @throws HttpFailException request fail exception
     */
    public static String bodyPost(String url, Object params, Map<String, String> headers, String respCharset) throws HttpFailException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);

        if (Objects.nonNull(headers) && !headers.isEmpty()) {
            for (Map.Entry<String, String> headEntry : headers.entrySet()) {
                if (Objects.nonNull(headEntry)) {
                    post.addHeader(headEntry.getKey(), headEntry.getValue());
                }
            }
        }

        StringEntity stringEntity = new StringEntity(JSON.toJSONString(params), ContentType.APPLICATION_JSON);
        post.setEntity(stringEntity);
        log.info(String.format("http post request url: %s, params: %s ,headers: %s", url, stringEntity, JSON.toJSONString(headers)));

        CloseableHttpResponse response = null;
        try {
            response = client.execute(post);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                if (StringUtils.isNotBlank(respCharset)) {
                    String result = EntityUtils.toString(response.getEntity(), respCharset);
                    log.info(String.format("post request %s response is: %s", url, result));

                    return result;
                } else {
                    String result = EntityUtils.toString(response.getEntity(), Charsets.UTF_8);
                    log.info(String.format("post request %s response is: %s", url, result));

                    return result;
                }
            } else {
                throw new HttpFailException("http post request is fail");
            }
        } catch (IOException e) {
            log.error("http post request occur an io exception", e);
            throw new HttpFailException(e);
        } finally {
            try {
                if (Objects.nonNull(response)) {
                    response.close();
                }
                client.close();
            } catch (IOException e) {
                log.error("close client occur an io exception", e);
            }
        }
    }

    /**
     * form data http post
     *
     * @param url     request url
     * @param params  request params
     * @param headers request headers
     * @param encode  response result charset
     * @return response result
     * @throws HttpFailException request fail exception
     */
    public static String formPost(String url, Map<String, String> params, Map<String, String> headers, String encode) throws HttpFailException {
        if (encode == null) {
            encode = "UTF-8";
        }
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        HttpPost httPost = new HttpPost(url);

        if (headers != null && headers.size() > 0) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httPost.setHeader(entry.getKey(), entry.getValue());
            }
        }

        List<NameValuePair> paramList = new ArrayList<>();
        if (params != null && params.size() > 0) {
            Set<String> keySet = params.keySet();
            for (String key : keySet) {
                paramList.add(new BasicNameValuePair(key, params.get(key)));
            }
        }

        CloseableHttpResponse httpResponse = null;
        try {
            httPost.setEntity(new UrlEncodedFormEntity(paramList, encode));

            httpResponse = closeableHttpClient.execute(httPost);
            HttpEntity entity = httpResponse.getEntity();
            return EntityUtils.toString(entity, encode);
        } catch (UnsupportedEncodingException e) {
            log.error("http post request occur an encoding exception", e);
            throw new HttpFailException(e);
        } catch (ClientProtocolException e) {
            log.error("http post request occur a client protocol exception", e);
            throw new HttpFailException(e);
        } catch (IOException e) {
            log.error("http post request occur an io protocol exception", e);
            throw new HttpFailException(e);
        } finally {
            try {
                if (Objects.nonNull(httpResponse)) {
                    httpResponse.close();
                }
                closeableHttpClient.close();
            } catch (IOException e) {
                log.error("close client occur an io exception", e);
            }
        }
    }

    /**
     * get basic authorization header param
     *
     * @param userName auth user
     * @param pwd      auth password
     * @return encrypt auth string
     */
    public static String getBasicHeaderAuth(String userName, String pwd) {

        return "Basic " + Base64.getUrlEncoder().encodeToString((userName + ":" + pwd).getBytes());
    }
}