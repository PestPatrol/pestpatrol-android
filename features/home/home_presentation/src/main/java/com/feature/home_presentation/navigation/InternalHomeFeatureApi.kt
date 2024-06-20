package com.feature.home_presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.core.common.navigation_constants.HomeFeature
import com.feature.feature_api.FeatureApi
import com.feature.home_presentation.screen.article.ArticleScreen
import com.feature.home_presentation.screen.article_detail.ArticleDetailScreen
import com.feature.home_presentation.screen.home.HomeScreen
import com.feature.home_presentation.screen.profile.ProfileScreen
import com.feature.home_presentation.screen.snap_history.SnapHistoryScreen

object InternalHomeFeatureApi : FeatureApi {
    override fun registerGraph(
        navController: NavHostController,
        navGraphBuilder: NavGraphBuilder
    ) {
        navGraphBuilder.navigation<HomeFeature.NestedRoute>(
            startDestination = HomeFeature.HomeScreen
        ) {
            composable<HomeFeature.HomeScreen> {
                HomeScreen(navController = navController)
            }
            composable<HomeFeature.SnapHistoryScreen> {
                SnapHistoryScreen(navController = navController)
            }
            composable<HomeFeature.ProfileScreen> {
                ProfileScreen(navController = navController)
            }
            composable<HomeFeature.ArticleScreen> {
                ArticleScreen(navController = navController)
            }
            composable<HomeFeature.ArticleDetailScreen> {
                val args = it.toRoute<HomeFeature.ArticleDetailScreen>()
                ArticleDetailScreen(
                    articleId = args.articleId,
                    navController = navController
                )
            }
        }
    }
}