package am.bethel.application.navigation.navigation_component

import am.bethel.application.navigation.navigation_screen_component.BookmarkedScreenComponent
import am.bethel.application.navigation.navigation_screen_component.DetailsScreenComponent
import am.bethel.application.navigation.navigation_screen_component.ListScreenComponent
import am.bethel.application.navigation.navigation_screen_component.SearchScreenComponent
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.pushNew
import kotlinx.serialization.Serializable

class RootComponent(
    componentContext: ComponentContext
) : ComponentContext by componentContext {

    val navigation = StackNavigation<Configuration>()

    val childStack = childStack(
        source = navigation,
        serializer = Configuration.serializer(),
        initialConfiguration = Configuration.List,
        handleBackButton = true,
        childFactory = ::createChild
    )

    fun navigateTo(config: Configuration) {
        navigation.bringToFront(config)
    }

    fun navigateBack() {
        navigation.pop()
    }

    @OptIn(ExperimentalDecomposeApi::class)
    private fun createChild(
        config: Configuration,
        context: ComponentContext
    ): Child {
        return when (config) {
            is Configuration.Details -> Child.Details(
                DetailsScreenComponent(
                    componentContext = context,
                    index = config.index.toInt()
                )
            )

            is Configuration.List -> Child.List(
                ListScreenComponent(
                    componentContext = context,
                    onNavigateToDetailsScreen = {
                        navigation.pushNew(Configuration.Details(it.toString()))
                    }
                )
            )

            is Configuration.Bookmarked -> Child.Bookmarked(
                BookmarkedScreenComponent(
                    componentContext = context
                )
            )

            is Configuration.Search -> Child.Search(
                SearchScreenComponent(
                    componentContext = context
                )
            )
        }
    }

    sealed class Child {
        data class List(val component: ListScreenComponent) : Child()
        data class Bookmarked(val component: BookmarkedScreenComponent) : Child()
        data class Search(val component: SearchScreenComponent) : Child()
        data class Details(val component: DetailsScreenComponent) : Child()
    }

    @Serializable
    sealed class Configuration(val label: String) {
        @Serializable
        object List : Configuration("Ծանկ")

        @Serializable
        object Bookmarked : Configuration("Էջանշված")

        @Serializable
        object Search : Configuration("Որոնում")

        @Serializable
        data class Details(val index: String) : Configuration(index)
    }
}
