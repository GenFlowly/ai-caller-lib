package com.genflowly.aicallerlib.models

abstract class CandidateResponse<T1, T2> {
    abstract val content: T1
    abstract val finishReason: T2
}