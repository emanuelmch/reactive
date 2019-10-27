/*
 * Copyright (c) 2019 Emanuel Machado da Silva <emanuel.mch@gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package bill.reaktive

import bill.reaktive.test.signalOnSleepingThread
import org.junit.Test
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class ThreadingProcessorTests {

    @Test
    fun `onNext signals should arrive in the same order they were emitted`() {
        val threadPool = Executors.newCachedThreadPool()
        Publishers.elements(500, 200, 300, 100)
            .signalOnSleepingThread(threadPool, listOf(500, 200, 300, 100))
            .test()
                        .awaitTerminalSignal(2, TimeUnit.SECONDS)
//            threadPool.awaitTermination(5, TimeUnit.SECONDS)
//        Thread.sleep(TimeUnit.SECONDS.toMillis(8))
//                x
            .assertComplete()
            .assertEmittedValues(500, 200, 300, 100)

    }
}