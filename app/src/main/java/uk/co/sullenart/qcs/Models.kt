package uk.co.sullenart.qcs

import java.util.*

data class CommitEntry(
    val commit: Commit? = null,
    val author: Author? = null
)

data class Commit(
    val message: String = "",
    val author: CommitAuthor? = null,
    val committer: CommitCommitter? = null
)

data class CommitAuthor(
    val name: String = ""
)

data class CommitCommitter(
    val date: Date? = null
)

data class Author(
    val avatar_url: String = ""
)

