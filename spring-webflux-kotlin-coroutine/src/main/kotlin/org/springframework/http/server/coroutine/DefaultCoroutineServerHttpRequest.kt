/*
 * Copyright 2002-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.http.server.coroutine

import kotlinx.coroutines.experimental.channels.ReceiveChannel
import kotlinx.coroutines.experimental.reactive.open
import org.springframework.core.io.buffer.DataBuffer
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.server.reactive.ServerHttpRequest
import java.net.URI

class DefaultCoroutineServerHttpRequest(val request: ServerHttpRequest): CoroutineServerHttpRequest {
    override val body: ReceiveChannel<DataBuffer>
        get() = request.body.open()

    override fun getHeaders(): HttpHeaders = request.headers

    override fun getMethod(): HttpMethod = request.method

    override fun getURI(): URI = request.uri

    override fun mutate(): CoroutineServerHttpRequest.Builder = DefaultCoroutineServerHttpRequestBuilder(request.mutate())

    override fun extractServerHttpRequest(): ServerHttpRequest = request

    override fun getMethodValue(): String = request.methodValue
}