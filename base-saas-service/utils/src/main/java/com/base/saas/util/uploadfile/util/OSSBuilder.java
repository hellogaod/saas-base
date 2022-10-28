package com.base.saas.util.uploadfile.util;

import com.aliyun.oss.ClientBuilderConfiguration;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.auth.CredentialsProvider;

/**
 * @Author:
 * @Date: 2018/05/15 09:35
 * @Description:
 */
public interface OSSBuilder {
    /**
     * Uses the specified OSS Endpoint and Access Id/Access Key to create a new
     * {@link OSSClient} instance.
     *
     * @param endpoint
     *            OSS endpoint.
     * @param accessKeyId
     *            Access Key ID.
     * @param secretAccessKey
     *            Secret Access Key.
     */
    public OSS build(String endpoint, String accessKeyId, String secretAccessKey);

    /**
     * Uses the specified OSS Endpoint, a security token from AliCloud STS and
     * Access Id/Access Key to create a new {@link OSSClient} instance.
     *
     * @param endpoint
     *            OSS Endpoint.
     * @param accessKeyId
     *            Access Id from STS.
     * @param secretAccessKey
     *            Access Key from STS
     * @param securityToken
     *            Security Token from STS.
     */
    public OSS build(String endpoint, String accessKeyId, String secretAccessKey, String securityToken);

    /**
     * Uses a specified OSS Endpoint, Access Id, Access Key, Client side
     * configuration to create a {@link OSSClient} instance.
     *
     * @param endpoint
     *            OSS Endpoint.
     * @param accessKeyId
     *            Access Key ID.
     * @param secretAccessKey
     *            Secret Access Key.
     * @param config
     *            A {@link ClientBuilderConfiguration} instance. The method would use
     *            default configuration if it's null.
     */
    public OSS build(String endpoint, String accessKeyId, String secretAccessKey, ClientBuilderConfiguration config);

    /**
     * Uses specified OSS Endpoint, the temporary (Access Id/Access Key/Security
     * Token) from STS and the client configuration to create a new
     * {@link OSSClient} instance.
     *
     * @param endpoint
     *            OSS Endpoint.
     * @param accessKeyId
     *            Access Key Id provided by STS.
     * @param secretAccessKey
     *            Secret Access Key provided by STS.
     * @param securityToken
     *            Security token provided by STS.
     * @param config
     *            A {@link ClientBuilderConfiguration} instance. The method would use
     *            default configuration if it's null.
     */
    public OSS build(String endpoint, String accessKeyId, String secretAccessKey, String securityToken,
                     ClientBuilderConfiguration config);

    /**
     * Uses the specified {@link CredentialsProvider} and OSS Endpoint to create
     * a new {@link OSSClient} instance.
     *
     * @param endpoint
     *            OSS services Endpoint.
     * @param credsProvider
     *            Credentials provider which has access key Id and access Key
     *            secret.
     */
    public OSS build(String endpoint, CredentialsProvider credsProvider);

    /**
     * Uses the specified {@link CredentialsProvider}, client configuration and
     * OSS endpoint to create a new {@link OSSClient} instance.
     *
     * @param endpoint
     *            OSS services Endpoint.
     * @param credsProvider
     *            Credentials provider.
     * @param config
     *            client configuration.
     */
    public OSS build(String endpoint, CredentialsProvider credsProvider, ClientBuilderConfiguration config);
}
