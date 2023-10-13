import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WatchScreen(navController: NavHostController) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Coming Soon!!",
            modifier = Modifier.offset(110.dp, 250.dp),
            fontSize = 30.sp
        )
        Box() {
            Button(
                onClick = { navController.navigate("HomeScreen") },
                modifier = Modifier
                    .size(100.dp)
                    .offset(160.dp, 580.dp),
                colors = ButtonDefaults.buttonColors(Color.Black)
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Arrow",
                    modifier = Modifier.size(40.dp)
                )
            }
        }

    }
}