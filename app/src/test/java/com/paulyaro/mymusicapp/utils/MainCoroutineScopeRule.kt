package com.paulyaro.mymusicapp.utils


import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.rules.TestWatcher
import org.junit.runner.Description

/**
 * MainCoroutineRule installs a TestCoroutineDispatcher for Disptachers.Main.
 *
 * Since it extends TestCoroutineScope, you can directly launch coroutines on the MainCoroutineRule
 * as a [CoroutineScope]:
 *
 * ```
 * mainCoroutineRule.launch { aTestCoroutine() }
 * ```
 *
 * All coroutines started on [MainCoroutineScopeRule] must complete (including timeouts) before the test
 * finishes, or it will throw an exception.
 *
 * When using MainCoroutineRule you should always invoke runBlockingTest on it to avoid creating two
 * instances of [TestCoroutineDispatcher] or [TestCoroutineScope] in your test:
 *
 * ```
 * @Test
 * fun usingRunBlockingTest() = mainCoroutineRule.runBlockingTest {
 *     aTestCoroutine()
 * }
 * ```
 *
 * You may call [DelayController] methods on [MainCoroutineScopeRule] and they will control the
 * virtual-clock.
 *
 * ```
 * mainCoroutineRule.pauseDispatcher()
 * // do some coroutines
 * mainCoroutineRule.advanceUntilIdle() // run all pending coroutines until the dispatcher is idle
 * ```
 *
 * By default, [MainCoroutineScopeRule] will be in a *resumed* state.
 *
 * @param dispatcher if provided, this [TestCoroutineDispatcher] will be used.
 */
@ExperimentalCoroutinesApi
class MainCoroutineScopeRule(val dispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()) :
    TestWatcher(),
    TestCoroutineScope by TestCoroutineScope(dispatcher) {
    override fun starting(description: Description?) {
        super.starting(description)
        // If your codebase allows the injection of other dispatchers like
        // Dispatchers.Default and Dispatchers.IO, consider injecting all of them here
        // and renaming this class to `CoroutineScopeRule`
        //
        // All injected dispatchers in a test should point to a single instance of
        // TestCoroutineDispatcher.
        Dispatchers.setMain(dispatcher)
    }

    override fun finished(description: Description?) {
        super.finished(description)
        advanceUntilIdle()
        cleanupTestCoroutines()
        Dispatchers.resetMain()
    }
}
