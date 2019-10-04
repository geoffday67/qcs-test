package uk.co.sullenart.qcs

import io.reactivex.Single

class DataRepository(
    private val githubApi: GithubApi
) {
    fun getCommits(owner: String, repo: String): Single<List<CommitEntry>> =
        githubApi.getCommits(owner, repo)
}