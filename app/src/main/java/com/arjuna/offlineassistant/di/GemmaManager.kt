package com.arjuna.offlineassistant.di

import android.content.Context
import com.google.mediapipe.tasks.genai.llminference.LlmInference
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GemmaManager @Inject constructor(
    @ApplicationContext private val context: Context
) {


    private val llmInference: LlmInference by lazy {
        val modelPath = File(
            context.filesDir,
            "gemma3-1b-it-int4.task"
        ).absolutePath ?: ""

        val options = LlmInference.LlmInferenceOptions.builder()
            .setModelPath(modelPath)
            .setMaxTokens(1024)
            .build()

        LlmInference.createFromOptions(context, options)
    }

    //    private val test = LlmInferenceSession.
    fun generate(prompt: String): String {
        return llmInference.generateResponse(prompt)
    }




}