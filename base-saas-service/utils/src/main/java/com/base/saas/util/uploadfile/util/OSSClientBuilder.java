package com.base.saas.util.uploadfile.util;

import com.aliyun.oss.ClientBuilderConfiguration;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.auth.CredentialsProvider;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;

/**
 * @Author: wangtao
 * @Date: 2018/05/15 09:35
 * @Description:
 */
public class OSSClientBuilder implements OSSBuilder  {
    @Override
    public OSS build(String endpoint, String accessKeyId, String secretAccessKey) {
        return new OSSClient(endpoint, getDefaultCredentialProvider(accessKeyId, secretAccessKey));
    }

    @Override
    public OSS build(String endpoint, String accessKeyId, String secretAccessKey, String securityToken) {
        return new OSSClient(endpoint, getDefaultCredentialProvider(accessKeyId, secretAccessKey, securityToken),
                getClientConfiguration());
    }

    @Override
    public OSS build(String endpoint, String accessKeyId, String secretAccessKey, ClientBuilderConfiguration config) {
        return new OSSClient(endpoint, getDefaultCredentialProvider(accessKeyId, secretAccessKey),
                getClientConfiguration(config));
    }

    @Override
    public OSS build(String endpoint, String accessKeyId, String secretAccessKey, String securityToken,
                     ClientBuilderConfiguration config) {
        return new OSSClient(endpoint, getDefaultCredentialProvider(accessKeyId, secretAccessKey, securityToken),
                getClientConfiguration(config));
    }

    @Override
    public OSS build(String endpoint, CredentialsProvider credsProvider) {
        return new OSSClient(endpoint, credsProvider, getClientConfiguration());
    }

    @Override
    public OSS build(String endpoint, CredentialsProvider credsProvider, ClientBuilderConfiguration config) {
        return new OSSClient(endpoint, credsProvider, getClientConfiguration(config));
    }

    private static ClientBuilderConfiguration getClientConfiguration() {
        return new ClientBuilderConfiguration();
    }

    private static ClientBuilderConfiguration getClientConfiguration(ClientBuilderConfiguration config) {
        if (config == null) {
            config = new ClientBuilderConfiguration();
        }
        return config;
    }

    private static DefaultCredentialProvider getDefaultCredentialProvider(String accessKeyId, String secretAccessKey) {
        return new DefaultCredentialProvider(accessKeyId, secretAccessKey);
    }

    private static DefaultCredentialProvider getDefaultCredentialProvider(String accessKeyId, String secretAccessKey,
                                                                          String securityToken) {
        return new DefaultCredentialProvider(accessKeyId, secretAccessKey, securityToken);
    }
}
