package uk.co.sullenart.qcs.commits

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_commits.*
import uk.co.sullenart.qcs.BaseFragment
import uk.co.sullenart.qcs.CommitEntry
import uk.co.sullenart.qcs.MainApplication
import uk.co.sullenart.qcs.R
import javax.inject.Inject

class CommitsFragment : BaseFragment(), CommitsPresenter.View {
    @Inject
    lateinit var presenter: CommitsPresenter

    private val commitsAdapter = CommitsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity?.application as MainApplication).component.inject(this)
        presenter.view = this

        return inflater.inflate(R.layout.fragment_commits, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        commits_list.adapter = commitsAdapter
        presenter.start()
    }

    override fun onDestroy() {
        super.onDestroy()

        presenter.stop()
    }

    override fun showCommits(commits: List<CommitEntry>) {
        commitsAdapter.setEntries(commits)
    }
}