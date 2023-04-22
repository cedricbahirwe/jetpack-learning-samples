package com.cedricbahirwe.courses

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cedricbahirwe.courses.data.DataSource
import com.cedricbahirwe.courses.model.Topic
import com.cedricbahirwe.courses.ui.theme.CoursesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TopicsApp()
        }
    }
}

@Composable
fun TopicsApp() {
    CoursesTheme {
        TopicsGrid(topicList = DataSource.topics)
    }
}

@Composable
private fun TopicsGrid(topicList: List<Topic>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
        items(topicList) {
            TopicGridItem(it)
        }
    }
}

@Composable
fun TopicGridItem(topic: Topic, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .height(68.dp),
        shape = RoundedCornerShape(5.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(4.dp)

    ) {
        Row {
            Image(
                painter = painterResource(topic.imageResId),
                contentDescription = stringResource(topic.topicResId),
                modifier = Modifier
                    .size(68.dp),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .padding(8.dp)
            ) {
                Text(
                    text = stringResource(id = topic.topicResId),
                    style = MaterialTheme.typography.bodySmall
                )

                Row {
                    Image(
                        painter = painterResource(R.drawable.ic_grain),
                        contentDescription = stringResource(topic.topicResId),
                        modifier = Modifier
                            .size(15.dp),
                        contentScale = ContentScale.Crop,
                        colorFilter = ColorFilter.tint(color = Color.Black),
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = topic.quantity.toString(),
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TopicGridItemPreview() {
    TopicGridItem(Topic(R.string.photography, 321, R.drawable.photography))
}