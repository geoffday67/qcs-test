package uk.co.sullenart.qcs.commits

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.commit_item.view.*
import uk.co.sullenart.qcs.CommitEntry
import uk.co.sullenart.qcs.R
import java.text.SimpleDateFormat
import java.util.*

class CommitsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val entries = mutableListOf<CommitEntry>()

    class CommitViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        companion object {
            private val dateFormatter by lazy {
                SimpleDateFormat("d MMM yyyy HH:mm:ss", Locale.UK)
            }
        }

        fun bind(entry: CommitEntry) {
            containerView.commit_title.text = entry.commit?.message ?: ""
            containerView.author_name.text = entry.commit?.author?.name ?: ""
            containerView.commit_date.text =
                dateFormatter.format(entry.commit?.committer?.date ?: "")

            if (entry.author?.avatar_url != null) {
                Glide.with(containerView)
                    .load(entry.author.avatar_url)
                    .into(containerView.author_image)
            } else {
                Glide.with(containerView)
                    .clear(containerView.author_image)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        CommitViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.commit_item,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = entries.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CommitViewHolder).bind(entries[position])
    }

    fun setEntries(incoming: List<CommitEntry>) {
        entries.apply {
            clear()
            addAll(incoming)
        }
        notifyDataSetChanged()
    }
}