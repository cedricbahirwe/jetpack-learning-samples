package com.cedricbahirwe.superheroes

//import androidx.compose.animation.AnimatedVisibility
//import androidx.compose.animation.core.Spring.DampingRatioLowBouncy
//import androidx.compose.animation.core.Spring.StiffnessVeryLow
//import androidx.compose.animation.core.spring
//import androidx.compose.animation.fadeIn
//import androidx.compose.animation.fadeOut
//import androidx.compose.animation.slideInVertically
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.layout.sizeIn
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.itemsIndexed
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.Card
//import androidx.compose.material3.CardDefaults
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Surface
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.res.stringResource
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import com.cedricbahirwe.superheroes.model.Hero
//import com.cedricbahirwe.superheroes.model.HeroesRepository
//import com.cedricbahirwe.superheroes.ui.theme.SuperheroesTheme


import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.Spring.DampingRatioLowBouncy
import androidx.compose.animation.core.Spring.StiffnessVeryLow
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cedricbahirwe.superheroes.model.Hero
import com.cedricbahirwe.superheroes.model.HeroesRepository
import com.cedricbahirwe.superheroes.ui.theme.SuperheroesTheme

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun HeroesList(
    heroes: List<Hero>,
    modifier: Modifier = Modifier,
) {
    val visibleState = remember {
        MutableTransitionState(false).apply {
            // Start the animation immediately.
            targetState = true
        }
    }

    // Fade in entry animation for the entire list
    AnimatedVisibility(
        visibleState = visibleState,
        enter = fadeIn(
            animationSpec = spring(dampingRatio = DampingRatioLowBouncy)
        ),
        exit = fadeOut(),
        modifier = modifier
    ) {
        LazyColumn {
            itemsIndexed(heroes) { index, hero ->
                HeroListItem(
                    hero = hero,
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        // Animate each list item to slide in vertically
                        .animateEnterExit(
                            enter = slideInVertically(
                                animationSpec = spring(
                                    stiffness = StiffnessVeryLow,
                                    dampingRatio = DampingRatioLowBouncy
                                ),
                                initialOffsetY = { it * (index + 1) } // staggered entrance
                            )
                        )
                )
            }
        }
    }
}

@Composable
fun HeroListItem(
    hero: Hero,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = modifier,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .sizeIn(minHeight = 72.dp)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = stringResource(hero.nameRes),
                    style = MaterialTheme.typography.displaySmall
                )
                Text(
                    text = stringResource(hero.descriptionRes),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            Spacer(Modifier.width(16.dp))
            Box(
                modifier = Modifier
                    .size(72.dp)
                    .clip(RoundedCornerShape(8.dp))

            ) {
                Image(
                    painter = painterResource(hero.imageRes),
                    contentDescription = null,
                    alignment = Alignment.TopCenter,
                    contentScale = ContentScale.FillWidth
                )
            }
        }
    }
}

@Preview("Light Theme")
@Preview("Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun HeroPreview() {
    val hero = Hero(
        R.string.hero1,
        R.string.description1,
        R.drawable.android_superhero1
    )
    SuperheroesTheme {
        HeroListItem(hero = hero)
    }
}

@Preview("Heroes List")
@Composable
fun HeroesPreview() {
    SuperheroesTheme(darkTheme = false) {
        Surface (
            color = MaterialTheme.colorScheme.background
        ) {
            /* Important: It is not a good practice to access data source directly from the UI.
            In later units you will learn how to use ViewModel in such scenarios that takes the
            data source as a dependency and exposes heroes.
            */
            HeroesList(heroes = HeroesRepository.heroes)
        }
    }
}
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun HeroesScreen() {
//    Scaffold(
//        topBar = {
//            HeroesTopAppBar()
//        }
//    ) { it ->
//        LazyColumn(contentPadding = it) {
//            items(HeroesRepository.heroes) {
//                HeroesItem(
//                    hero = it,
//                    modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))
//                )
//            }
//        }
//    }
//}
//
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun HeroesTopAppBar(modifier: Modifier = Modifier) {
//    CenterAlignedTopAppBar(
//        title = {
//            Text(
//                text = stringResource(R.string.app_name),
//                style = MaterialTheme.typography.displayLarge
//            )
//        },
//        modifier = modifier
//    )
//}
//@Composable
//fun HeroesItem(hero: Hero,
//               modifier: Modifier = Modifier) {
//    Card(
//        modifier = modifier,
//        elevation = CardDefaults.cardElevation(2.dp)
//
//    ) {
//
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(dimensionResource(R.dimen.padding_small))
//        ) {
//
//            HeroInformation(hero.nameRes, hero.descriptionRes)
//            Spacer(Modifier.width(16.dp))
//
//            HeroIcon(hero.imageRes)
//        }
//    }
//}
//
//@Composable
//fun HeroIcon(
//    @DrawableRes heroIcon: Int,
//) {
//    Box(
//        modifier = Modifier
//            .size(72.dp)
//            .clip(RoundedCornerShape(8.dp))
//
//    ) {
//        Image(
//            painter = painterResource(heroIcon),
//            contentDescription = null,
//            alignment = Alignment.TopCenter,
//            contentScale = ContentScale.FillWidth
//        )
//    }
////    Box(modifier = Modifier.fillMaxSize().size(dimensionResource(R.dimen.image_size))) {
////        Image(
////            modifier = modifier
////                .fillMaxSize()
////                .padding(dimensionResource(R.dimen.padding_small))
////                .clip(MaterialTheme.shapes.small),
////            painter = painterResource(heroIcon),
////            contentScale = ContentScale.FillBounds,
////
////            // Content Description is not needed here - image is decorative, and setting a null content
////            // description allows accessibility services to skip this element during navigation.
////
////            contentDescription = null,
////        )
////    }
//
//}
//
//@Composable
//fun HeroInformation(
//    @StringRes heroName: Int,
//    @StringRes heroDescription: Int,
//    modifier: Modifier = Modifier
//) {
//    Column(modifier = modifier) {
//        Text(
//            text = stringResource(heroName),
//            style = MaterialTheme.typography.displayMedium,
//        )
//        Text(
//            text = stringResource(heroDescription),
//            style = MaterialTheme.typography.bodyLarge
//        )
//    }
//}
//
//@Preview
//@Composable
//fun WoofDarkThemePreview() {
//    SuperheroesTheme(darkTheme = false) {
//        HeroesItem(HeroesRepository.heroes[2])
//    }
//}