// Copyright 2018 Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: Apache-2.0

package software.aws.toolkits.jetbrains.services.lambda.execution

import com.intellij.execution.configurations.ConfigurationTypeBase
import com.intellij.execution.configurations.ConfigurationTypeUtil
import icons.AwsIcons
import software.aws.toolkits.jetbrains.services.lambda.execution.local.LambdaLocalRunConfigurationFactory
import software.aws.toolkits.jetbrains.services.lambda.execution.remote.LambdaRemoteRunConfigurationFactory
import software.aws.toolkits.resources.message

class LambdaRunConfiguration :
    ConfigurationTypeBase(
        "aws.lambda",
        message("lambda.service_name"),
        message("lambda.run_configuration.description"),
        AwsIcons.Logos.LAMBDA
    ) {
    init {
        addFactory(LambdaLocalRunConfigurationFactory(this))
        addFactory(LambdaRemoteRunConfigurationFactory(this))
    }

    companion object {
        fun getInstance() = ConfigurationTypeUtil.findConfigurationType(LambdaRunConfiguration::class.java)
    }
}