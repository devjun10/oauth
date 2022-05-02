package com.example.oauth.common.login.token.google;

import com.example.oauth.common.login.token.WebToken;
import com.example.oauth.common.login.token.WebTokenProvider;
import com.example.oauth.common.login.token.configuration.ClientRegistration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Map;

import static org.springframework.http.HttpHeaders.ACCEPT;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@Component(value = "GoogleWebTokenUtils")
public class GoogleWebTokenProvider implements WebTokenProvider {

    public static final Class<? extends WebToken> GOOGLE = GoogleWebToken.class;
    private static final String CLIENT_ID = "client_id";
    private static final String CLIENT_SECRET = "client_secret";
    private static final String CODE = "code";
    private static final String GRANT_TYPE = "grant_type";
    private static final String REDIRECT_URI = "redirect_uri";
    private static final String AUTHORIZATION_CODE = "authorization_code";

    @Override
    public WebToken getWebToken(ClientRegistration clientRegistration, String code) {
        return null;
    }

    @Override
    public HttpEntity<?> getAccessTokenRequest(ClientRegistration clientRegistration, String code) {
        Assert.notNull(clientRegistration, "Registration must be not null.");
        Assert.notNull(code, "Code must be not null.");
        MultiValueMap<String, String> headers = getHeader();
        MultiValueMap<String, String> payLoad = getPayLoad(clientRegistration, code);
        return new HttpEntity<>(payLoad, headers);
    }

    @Override
    public MultiValueMap<String, String> getHeader() {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.set(ACCEPT, APPLICATION_JSON_VALUE);
        return headers;
    }

    @Override
    public MultiValueMap<String, String> getPayLoad(ClientRegistration clientRegistration, String code) {
//        try {
//            URL url = new URL(GOOGLE_SNS_TOKEN_BASE_URL);
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setRequestMethod("POST");
//            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//            conn.setDoOutput(true);
//
//            Map<String, Object> params = new HashMap<>();
//            params.put(CODE, code);
//            params.put(CLIENT_ID, clientRegistration.getClientId());
//            params.put(CLIENT_SECRET, clientRegistration.getClientSecret());
//            params.put(REDIRECT_URI, clientRegistration.getRedirectUrl());
//            params.put(GRANT_TYPE, "authorization_code");
//
//            String parameterString = params.entrySet().stream()
//                    .map(x -> x.getKey() + "=" + x.getValue())
//                    .collect(Collectors.joining("&"));
//
//            BufferedOutputStream bous = new BufferedOutputStream(conn.getOutputStream());
//            bous.write(parameterString.getBytes());
//            bous.flush();
//            bous.close();
//
//            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//
//            StringBuilder sb = new StringBuilder();
//            String line;
//
//            while ((line = br.readLine()) != null) {
//                sb.append(line);
//            }
//
//            if (conn.getResponseCode() == 200) {
//                return sb.toString();
//            }
//            return "구글 로그인 요청 처리 실패";
//        } catch (IOException | ProtocolException e) {
//            throw new IllegalArgumentException("알 수 없는 구글 로그인 Access Token 요청 URL 입니다 :: " + GOOGLE_SNS_TOKEN_BASE_URL);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ProtocolException e) {
//            e.printStackTrace();
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        MultiValueMap<String, String> payLoad = new LinkedMultiValueMap<>();
        payLoad.set(CODE, code);
        payLoad.set(CLIENT_ID, clientRegistration.getClientId());
        payLoad.set(CLIENT_SECRET, clientRegistration.getClientSecret());
        payLoad.set(REDIRECT_URI, clientRegistration.getRedirectUrl());
        payLoad.set(GRANT_TYPE, AUTHORIZATION_CODE);
        return payLoad;
    }

    @Override
    public HttpHeaders getAuthorizationIncludedHeader(String accessToken) {
        return null;
    }

    @Override
    public Map<String, String> getUserDetail(String body) {
        return null;
    }

    @Override
    public Map<String, String> getUserDetailFrom(ClientRegistration clientRegistration, WebToken gitWebToken) {
        return null;
    }
}
