package com.ilostjobs.course.layout

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.chrisbanes.accompanist.coil.CoilImage
import kotlinx.coroutines.launch

@Composable
fun AppScreen() {
  SampleList()
}

@Composable
fun SampleList(size: Int = 100) {
  val state = rememberLazyListState()
  val coroutineScope = rememberCoroutineScope()
  Column() {
    Row() {
      Button(onClick = {
        coroutineScope.launch {
          state.animateScrollToItem(0, 0)
        }
      }) {
        Text("Scroll To The Top")
      }
      Button(onClick = {
        coroutineScope.launch {
          state.animateScrollToItem(size - 1, 0)
        }
      }) {
        Text("Scroll To The End")
      }
    }
    LazyColumn(state = state, content = {
      items(size) { index ->
        PhotographerCard(index)
        if (index < (size - 1)) {
          Spacer(modifier = Modifier.height(5.dp))
        }
      }
    })
  }
}

@Composable
fun PhotographerCard(index: Int, modifier: Modifier = Modifier) {
  Surface(
    color = Color.LightGray,
    shape = RoundedCornerShape(8.dp),
    modifier = Modifier.padding(horizontal = 8.dp)
  ) {
    Row(modifier = modifier
        .fillMaxWidth()
        .clickable { }
        .padding(16.dp)) {
      Surface(
        modifier = Modifier.size(50.dp),
        shape = CircleShape,
        color = MaterialTheme.colors.onSurface
      ) {
        CoilImage(
          data = "https://developer.android.com/images/brand/Android_Robot.png",
          contentDescription = "Android Logo",
          modifier = Modifier.size(50.dp),
          contentScale = ContentScale.Inside
        )
      }
      Column(
        modifier = Modifier
            .padding(start = 8.dp)
            .align(Alignment.CenterVertically)
      ) {
        Text("Alfred Sisley $index", fontWeight = FontWeight.Bold)
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
          Text(text = "3 minutes ago", style = MaterialTheme.typography.body2)
        }
      }

    }
  }
}

@Preview
@Composable
fun Preview() {
  AppScreen()
}