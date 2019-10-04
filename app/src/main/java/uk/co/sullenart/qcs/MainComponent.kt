package uk.co.sullenart.qcs

import dagger.Component
import uk.co.sullenart.qcs.commits.CommitsFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [MainModule::class])
interface MainComponent {
    fun inject(fragment: BaseFragment)
    fun inject(fragment: CommitsFragment)
}
