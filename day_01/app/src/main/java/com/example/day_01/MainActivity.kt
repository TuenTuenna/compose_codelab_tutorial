package com.example.day_01

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.day_01.ui.theme.Day_01Theme
import com.example.day_01.ui.theme.Green200
import com.example.day_01.ui.theme.Red200
import com.example.day_01.ui.theme.Teal200

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Day_01Theme {
                // A surface container using the 'background' color from the theme
                ContentView()

            }
        }
    }
}

@Composable
private fun ContentView(){

    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }

    if (shouldShowOnboarding) {
        OnboardingScreen(onContinueClicked = {
            Log.d("TAG", "ContentView: 온보딩 클릭됨")
            shouldShowOnboarding = !shouldShowOnboarding
        })
    } else {
//        MyColumn(modifier = Modifier.padding(20.dp))
        MyLazyColumn(modifier = Modifier.padding(20.dp))
    }

//    Surface(color = Green200) {
////        HelloWorld(modifier = Modifier.padding(all = 10.dp))
//        MyColumn(modifier = Modifier.padding(10.dp))
//    }

}

@Composable
fun MyColumn(fruits: List<String> = listOf("바나나", "딸기", "포도"), modifier: Modifier) {
    Column (modifier = modifier) {
        for (aFruitName in fruits) {
            FruitView(aFruitName)
        }
    }
}

@Composable
fun MyLazyColumn(fruits: List<String> = List(1000) { "번호 : $it" } , modifier: Modifier) {
    LazyColumn (modifier = modifier) {
        items(items = fruits) { aFruit ->
            FruitView(name = aFruit)
        }
    }
}


//- `val mutableState = remember { mutableStateOf(default) }`
//- `var value by remember { mutableStateOf(default) }`
//- `val (value, setValue) = remember { mutableStateOf(default) }`

@Composable
fun FruitView(name: String){

    val expanded = rememberSaveable { mutableStateOf(false) }

    var isOpen by remember { mutableStateOf(false) }

    val (shouldOpen, setShouldOpen) = remember { mutableStateOf(false) }

//    val extraPadding = if (expanded.value) 48.dp else 0.dp

    val extraPadding by animateDpAsState(
        if (expanded.value) 48.dp else 0.dp,
//        animationSpec = spring(
//            dampingRatio = Spring.DampingRatioMediumBouncy,
//            stiffness = Spring.StiffnessLow
//        )
    )

//    val extraPadding = animateDpAsState(
//        if (expanded.value) 48.dp else 0.dp,
//        animationSpec = spring(
//            dampingRatio = Spring.DampingRatioMediumBouncy,
//            stiffness = Spring.StiffnessLow
//        )
//    )

    Surface(color = Red200,
        modifier = Modifier
            .padding(bottom = extraPadding)
            .clickable { Log.d("TAG", "FruitView: $name") })
    {
        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(end = 10.dp)
                .fillMaxWidth(fraction = 1.0f)
        ) {
            Text(text = name,
                modifier = Modifier
//                    .fillMaxWidth(fraction = 1.0f)
                    .padding(10.dp)
                    .background(Teal200)
                    .weight(0.5f),
                style = MaterialTheme.typography.h4.copy(
                    fontWeight = FontWeight.ExtraBold
                )
            )
            OutlinedButton(
//                modifier = Modifier.weight(0.5f) ,
                onClick = {
                    expanded.value = !expanded.value
//                    isOpen = !isOpen
//                    setShouldOpen(!shouldOpen)
                Log.d("TAG", "FruitView: 버튼 클릭 $name")
            }) {
                Text(text = if (expanded.value) "열렸다" else "닫혔다")
//                Text(text = if (isOpen) "열렸다" else "닫혔다")
//                Text(text = if (shouldOpen) "열렸다" else "닫혔다")
            }
        }
    }
}

@Composable
fun OnboardingScreen(onContinueClicked: () -> Unit) {
    // TODO: This state should be hoisted
    var shouldShowOnboarding by remember { mutableStateOf(true) }

    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Welcome to the Basics Codelab!")
            Button(
                modifier = Modifier.padding(vertical = 24.dp),
                onClick = onContinueClicked
            ) {
                Text("Continue")
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "오늘도 $name!")
}

//@Composable
//private fun Greeting(name: String) {
//    Surface(color = MaterialTheme.colors.primary) {
//        Text(text = "Hello $name!", modifier = Modifier.padding(24.dp))
//    }
//}

@Composable
fun HelloWorld(modifier: Modifier) {
    Surface(color = Green200, modifier = modifier) {
        Text(text = "HELLO WORLD", color = Color.White, modifier = Modifier.padding(all = 30.dp))
    }
}

@Preview(
    showBackground = true,
    widthDp = 320,
    uiMode = UI_MODE_NIGHT_NO,
    name = "DefaultPreviewDark"
)
@Composable
fun DefaultPreview() {
    Day_01Theme {
//        ContentView()
        MyLazyColumn(modifier = Modifier.padding(20.dp))
    }
}
