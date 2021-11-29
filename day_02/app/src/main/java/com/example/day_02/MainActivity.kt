package com.example.day_02

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.day_02.ui.theme.*
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Day_02Theme {
                // A surface container using the 'background' color from the theme

                LayoutsCodelab()

//                Surface(color = MaterialTheme.colors.background) {
//                    PhotographerCard(modifier = Modifier.fillMaxWidth(1f)){
//                        Column() {
//                            Text(text = "하하하")
//                            Text(text = "하하하")
//                            Text(text = "하하하")
//                            Text(text = "하하하")
//                        }
//                    }
////                    PhotographerCard()
//                }
            }
        }
    }
}

@Composable
fun LayoutsCodelab() {

    val coroutineScope = rememberCoroutineScope()

    val scrollState = rememberScrollState()
    val lazyListScrollState = rememberLazyListState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "LayoutsCodelab")
                },
                actions = {
                    IconButton(onClick = {
                        Log.d("TAG", "LayoutsCodelab: 좋아요 클릭")
                    }) {
                        Icon(Icons.Filled.Favorite, contentDescription = null)
                    }

                    IconButton(onClick = {
                        Log.d("TAG", "LayoutsCodelab: 홈 클릭")
                    }) {
                        Icon(Icons.Filled.Home, contentDescription = null)
                    }
                }
            )
        },
        bottomBar = {
            BottomAppBar() {
                IconButton(onClick = {
                    Log.d("TAG", "LayoutsCodelab: 좋아요 클릭")
                }) {
                    Icon(Icons.Filled.Favorite, contentDescription = null)
                }

                IconButton(onClick = {
                    Log.d("TAG", "LayoutsCodelab: 홈 클릭")
                }) {
                    Icon(Icons.Filled.Home, contentDescription = null)
                }
            }
        },

        drawerContent = {
            IconButton(onClick = {
                Log.d("TAG", "LayoutsCodelab: 좋아요 클릭")
            }) {
                Icon(Icons.Filled.Favorite, contentDescription = null)
            }

            IconButton(onClick = {
                Log.d("TAG", "LayoutsCodelab: 홈 클릭")
            }) {
                Icon(Icons.Filled.Home, contentDescription = null)
            }
        },
        floatingActionButton = {
            IconButton(onClick = {
                Log.d("TAG", "LayoutsCodelab: 홈 클릭")
                coroutineScope.launch {
//                    scrollState.scrollTo(0)
//                    scrollState.animateScrollTo(0)
                    lazyListScrollState.animateScrollToItem(0)
                }
            }) {
                Icon(Icons.Filled.ArrowUpward, contentDescription = null)
            }
        }
    ) { innerPadding ->
        BodyContent(
            Modifier
                .padding(innerPadding)
                .padding(horizontal = 20.dp), contentSlot = {
//            SomeText()
//                SimpleList(scrollState)
//                    LazySimpleList(scrollState = lazyListScrollState)

        })
    }
}

//@Composable
//fun CustomLayout(
//    modifier: Modifier = Modifier,
//    // custom layout attributes
//    content: @Composable () -> Unit
//) {
//    Layout(
//        modifier = modifier,
//        content = content
//    ) { measurables, constraints ->
//        // measure and position children given constraints logic here
//    }
//}


@Composable
fun ImageListItem(index: Int) {
    Row(verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Image(
            painter = rememberImagePainter(
                data = "https://developer.android.com/images/brand/Android_Robot.png"
            ),
            contentDescription = "",
            modifier = Modifier.size(50.dp)
        )
//        Spacer(Modifier.width(10.dp))
        Text("Item #$index", style = MaterialTheme.typography.subtitle1)
    }
}

@Composable
fun SimpleList(scrollState : ScrollState) {
    // We save the scrolling position with this state that can also
    // be used to programmatically scroll the list
//    val scrollState = rememberScrollState()

    val getBGColor : (Int) -> Color = { index ->
        if (index == 0) Color.Red else Color.Yellow
    }

    Column(Modifier.verticalScroll(scrollState)) {
        repeat(100) {
            Text("Item #$it",
                modifier = Modifier
                    .fillMaxWidth()
                    .background(getBGColor(it))
            )
        }
    }
//    Column() {
//        repeat(100) {
//            Text("Item #$it")
//        }
//    }
}


@Composable
fun LazySimpleList(scrollState : LazyListState) {
    // We save the scrolling position with this state that can also
    // be used to programmatically scroll the list
//    val scrollState = rememberScrollState()

    val getBGColor : (Int) -> Color = { index ->
        if (index == 0) Color.Red else Color.Yellow
    }

    LazyColumn(state = scrollState) {
        items(100) {
//            Text("Item #$it",
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .background(getBGColor(it))
//            )
            ImageListItem(it)
        }
    }
//    Column() {
//        repeat(100) {
//            Text("Item #$it")
//        }
//    }
}

@Composable
fun SomeText(){
    Column() {
        Text(text = "Hi there!")
        Text(text = "Thanks for going through the Layouts codelab")
        Text(text = "Thanks for going through the Layouts codelab")
        Text(text = "Thanks for going through the Layouts codelab")
    }
}

@Composable
fun BodyContent(modifier: Modifier = Modifier, contentSlot : @Composable () -> Unit) {

    Surface(
        color = Blue200,
        modifier = modifier,
        content = contentSlot
    )

//    Surface(
//        color = Blue200,
//        modifier = modifier,
//        content = {
//            Column() {
//                Text(text = "Hi there!")
//                Text(text = "Thanks for going through the Layouts codelab")
//            }
//        }
//    )
//    Surface() {
//        Column(
//            modifier = modifier.background(Blue200)
//        ) {
//            Text(text = "Hi there!")
//            Text(text = "Thanks for going through the Layouts codelab")
//        }
//    }
}


@Composable
fun PhotographerCard(modifier: Modifier = Modifier,
                     contentSlot: @Composable () -> Unit) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .background(Blue200)
            .padding(all = 10.dp)
            .height(300.dp)
    ) {
        Surface(
            modifier = Modifier
                .size(80.dp)
                .weight(0.3f)
                .clickable { Log.d("TAG", "PhotographerCard: 클릭") }
                .clip(RoundedCornerShape(topStart = 10.dp, bottomEnd = 10.dp))
                .background(Color.Red)
                .padding(10.dp)
                .background(Color.Magenta)
                .padding(10.dp)
                .background(Color.Yellow)
            ,
            shape = CircleShape,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.5f)) {
        }

        Surface(
            modifier = Modifier
                .background(Color.Yellow)
                .weight(0.7f)
                .fillMaxHeight(),
            content = contentSlot)

//        Column(
//            verticalArrangement = Arrangement.spacedBy(3.dp),
//            modifier = Modifier
//                .background(Color.Yellow)
//                .weight(0.7f)
//                .fillMaxHeight()
//        ){
//            Text("Alfred Sisley", fontWeight = FontWeight.Bold,
//                modifier = Modifier
//                    .background(Purple500)
//                    .weight(1f))
//            // LocalContentAlpha is defining opacity level of its children
//            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
//                Text("3 minutes ago", style = MaterialTheme.typography.body2,
//                    modifier = Modifier
//                        .background(Color.Green)
//                        .weight(2f))
//            }
//            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.disabled) {
//                Text("이용불가", style = MaterialTheme.typography.caption,
//                    modifier = Modifier
//                        .background(Purple200)
//                        .weight(2f))
//            }
//        }
    }
}


@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}
//
//@Preview(showBackground = true)
//@Composable
//fun PhotographerCardPreview() {
//    Day_02Theme {
////        Greeting("Android")
//        PhotographerCard()
//    }
//}
