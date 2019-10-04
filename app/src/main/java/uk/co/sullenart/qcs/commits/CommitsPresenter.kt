package uk.co.sullenart.qcs.commits

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import uk.co.sullenart.qcs.BasePresenter
import uk.co.sullenart.qcs.CommitEntry
import uk.co.sullenart.qcs.DataRepository
import javax.inject.Inject

class CommitsPresenter @Inject constructor(
    private val dataRepository: DataRepository
) : BasePresenter() {
    lateinit var view: View

    fun start() {
        refreshCommits()
    }

    private fun refreshCommits() {
        add(
            dataRepository.getCommits("JetBrains", "kotlin")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy { view.showCommits(it) }
        )
    }

    interface View {
        fun showCommits(commits: List<CommitEntry>)
    }
}