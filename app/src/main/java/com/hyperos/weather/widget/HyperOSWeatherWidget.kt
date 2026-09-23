
/*
 * HyperOS 2 Glassmorphism Weather Widget Provider (4x2 Size)
 * Created for Android 12+ / Xiaomi HyperOS 2
 */

package com.hyperos.weather.widget

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.*
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.color.ColorProvider
import androidx.compose.ui.graphics.Color
import java.text.SimpleDateFormat
import java.util.*

class HyperOSWeatherWidget : GlanceAppWidget() {

    override async fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            WidgetContent()
        }
    }

    @Composable
    private fun WidgetContent() {
        val currentTime = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())
        val currentDate = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
        val dayName = SimpleDateFormat("EEEE", Locale("vi", "VN")).format(Date())
        
        // Root Frosted Glass Container (4x2 Aspect Ratio)
        Column(
            modifier = GlanceModifier
                .fillMaxSize()
                .padding(12.dp)
                .background(Color(0x33FFFFFF))
        ) {
            // Top Section: Clock & Date | Divider | Weather
            Row(
                modifier = GlanceModifier.fillMaxWidth().defaultWeight(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Clock & Date Block
                Row(
                    modifier = GlanceModifier.defaultWeight(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = currentTime,
                        style = TextStyle(
                            fontSize = 36.sp,
                            fontWeight = FontWeight.Normal,
                            color = ColorProvider(Color.White)
                        )
                    )
                    Spacer(modifier = GlanceModifier.width(8.dp))
                    Column {
                        Text(
                            text = "$dayName $currentDate",
                            style = TextStyle(fontSize = 11.sp, color = ColorProvider(Color.White))
                        )
                        Text(
                            text = "Âm lịch: 28/03",
                            style = TextStyle(fontSize = 11.sp, fontWeight = FontWeight.Bold, color = ColorProvider(Color(0xFFFDE047)))
                        )
                        Text(
                            text = "(Ất Tỵ)",
                            style = TextStyle(fontSize = 10.sp, color = ColorProvider(Color(0xFFFEF08A)))
                        )
                    }
                }

                // Divider Line
                Spacer(modifier = GlanceModifier.width(1.dp).fillMaxHeight().background(Color(0x33FFFFFF)))

                // Weather Block
                Column(
                    horizontalAlignment = Alignment.Start,
                    modifier = GlanceModifier.padding(start = 8.dp)
                ) {
                    Text(
                        text = "26°C",
                        style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Light, color = ColorProvider(Color.White))
                    )
                    Text(
                        text = "📍 Hà Nội",
                        style = TextStyle(fontSize = 11.sp, fontWeight = FontWeight.Bold, color = ColorProvider(Color.White))
                    )
                    Text(
                        text = "Có mây",
                        style = TextStyle(fontSize = 10.sp, color = ColorProvider(Color(0xCCFFFFFF)))
                    )
                }
            }

            // Horizontal Divider
            Spacer(modifier = GlanceModifier.fillMaxWidth().height(1.dp).background(Color(0x33FFFFFF)))

            // Bottom Section: 3-Day Forecast
            Row(
                modifier = GlanceModifier.fillMaxWidth().defaultWeight(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ForecastColumn("Thứ 7", "24° / 32°", "Có mây")
                ForecastColumn("CN", "23° / 30°", "Mưa nhẹ")
                ForecastColumn("Thứ 2", "22° / 31°", "Có mây")
            }
        }
    }

    @Composable
    private fun ForecastColumn(day: String, temp: String, desc: String) {
        Column(horizontalAlignment = Alignment.Start) {
            Text(text = day, style = TextStyle(fontSize = 10.sp, fontWeight = FontWeight.Bold, color = ColorProvider(Color.White)))
            Text(text = temp, style = TextStyle(fontSize = 10.sp, color = ColorProvider(Color.White)))
            Text(text = desc, style = TextStyle(fontSize = 9.sp, color = ColorProvider(Color(0xAAFFFFFF))))
        }
    }
}
                    