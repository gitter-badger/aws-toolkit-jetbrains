// Copyright 2018 Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: Apache-2.0

package software.aws.toolkits.jetbrains.core.credentials

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.auth.credentials.AwsCredentials
import software.aws.toolkits.core.credentials.ToolkitCredentialsProvider
import software.aws.toolkits.core.region.AwsRegion
import software.aws.toolkits.jetbrains.core.region.AwsRegionProvider

class MockProjectAccountSettingsManager : ProjectAccountSettingsManager {
    override var activeRegion = AwsRegionProvider.getInstance().defaultRegion()

    override var activeCredentialProvider = object : ToolkitCredentialsProvider() {
        override val id = "MockCredentials"
        override val displayName = " Mock Credentials"

        override fun resolveCredentials(): AwsCredentials = AwsBasicCredentials.create("Foo", "Bar")
    }

    override fun recentlyUsedRegions(): List<AwsRegion> {
        TODO("not implemented")
    }

    override fun recentlyUsedCredentials(): List<ToolkitCredentialsProvider> {
        TODO("not implemented")
    }
}