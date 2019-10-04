package uk.co.sullenart.qcs.commits

import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import uk.co.sullenart.qcs.Commit
import uk.co.sullenart.qcs.CommitEntry
import uk.co.sullenart.qcs.DataRepository

class CommitsPresenterTest {
    val mockView: CommitsPresenter.View = mockk(relaxUnitFun = true)
    val mockDataRepository: DataRepository = mockk()

    lateinit var presenter: CommitsPresenter

    companion object {
        @BeforeClass
        @JvmStatic
        fun init() {
            RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
            RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
            RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        }
    }

    @Before
    fun setup() {
        clearAllMocks()
        presenter = CommitsPresenter(mockDataRepository).apply {
            view = mockView
        }
    }

    @Test
    fun `Starting the presenter should refresh the commits`() {
        val commits = listOf(
            CommitEntry(Commit(message = "First commit")),
            CommitEntry(Commit(message = "Second commit")),
            CommitEntry(Commit(message = "Third commit"))
        )
        every { mockDataRepository.getCommits(any(), any()) } returns Single.just(commits)

        presenter.start()

        verify { mockView.showCommits(commits) }
    }
}