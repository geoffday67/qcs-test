package uk.co.sullenart.qcs

import android.content.Context
import androidx.fragment.app.Fragment
import javax.inject.Inject

open class BaseFragment : Fragment() {
    @Inject protected lateinit var dataRepository: DataRepository

    protected lateinit var safeContext: Context

    override fun onAttach(context: Context) {
        safeContext = context
        (activity?.application as MainApplication).component.inject(this)

        super.onAttach(context)
    }
}
