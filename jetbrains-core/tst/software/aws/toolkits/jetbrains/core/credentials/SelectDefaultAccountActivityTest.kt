// Copyright 2018 Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: Apache-2.0

package software.aws.toolkits.jetbrains.core.credentials

import com.intellij.testFramework.ProjectRule
import com.nhaarman.mockitokotlin2.mock
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import software.aws.toolkits.core.credentials.CredentialProviderNotFound
import software.aws.toolkits.core.credentials.ToolkitCredentialsProvider

class SelectDefaultAccountActivityTest {
    @Rule
    @JvmField
    val projectRule = ProjectRule()

    private val sut = SelectDefaultAccountActivity()
    private lateinit var mockCredentialManager: MockCredentialsManager
    private lateinit var settingsManager: MockProjectAccountSettingsManager

    @Before
    fun setup() {
        mockCredentialManager = CredentialManager.getInstance() as MockCredentialsManager
        settingsManager = ProjectAccountSettingsManager.getInstance(projectRule.project) as MockProjectAccountSettingsManager
    }

    @Test
    fun activeCredentialsAreUnAltered() {
        val currentlySelectedProvider = mock<ToolkitCredentialsProvider>()
        settingsManager.internalProvider = currentlySelectedProvider

        sut.runActivity(projectRule.project)

        assertThat(settingsManager.activeCredentialProvider).isEqualTo(currentlySelectedProvider)
    }

    @Test
    fun defaultProfileIsSelectedWhenNoActiveCredentials() {
        mockCredentialManager.addCredentials("profile:default", mock())
        sut.runActivity(projectRule.project)

        assertThat(settingsManager.activeCredentialProvider.id).isEqualTo("profile:default")
    }

    @Test
    fun noDefaultAndNoActiveThrows() {
        settingsManager.internalProvider = null

        sut.runActivity(projectRule.project)

        assertThatExceptionOfType(CredentialProviderNotFound::class.java).isThrownBy { settingsManager.activeCredentialProvider }
    }
}