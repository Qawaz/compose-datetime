# Compose Date Time Picker

### Multiplatform

- [x] JVM , Android & JS
- [x] Material3
- [x] Compose 1.4.0
- [x] Kotlin 1.8.0
- [x] Kotlinx DateTime

![](https://raw.githubusercontent.com/vanpra/compose-material-dialogs/main/imgs/date_and_time.png)

### Usage

```kotlin

// Constructing a modifier to use on all pickers 
val pickerModifier = Modifier.padding(horizontal = 16.dp).widthIn(max = 420.dp).clip(RoundedCornerShape(4.dp))
    .background(color = MaterialTheme.colors.background).padding(bottom = 8.dp)

// Simple Date Picker without a dialog
// You must use your own dialog on whichever platform you are
DatePicker(
    modifier = pickerModifier,
    onDateChange = {
        println(it.toString())
    }
)

// Simple Time Picker without a dialog
TimePicker(
    modifier = pickerModifier,
    onTimeChange = {
        println(it.toString())
    }
)

// TimePicker with a time range restriction
TimePicker(
    modifier = pickerModifier, state = rememberTimePickerState(
        timeRange = LocalTime(9, 35)..LocalTime(21, 13), is24HourClock = false
    ),
    onTimeChange = {
        println(it.toString())
    }
)

// Time Picker with a 24 hour clock
TimePicker(
    modifier = pickerModifier, state = rememberTimePickerState(is24HourClock = true),
    onTimeChange = {
        println(it.toString())
    }
)


// Time Picker with a time range restriction and a 24 hour clock
TimePicker(
    modifier = pickerModifier, state = rememberTimePickerState(
        timeRange = LocalTime(9, 35)..LocalTime(21, 13), is24HourClock = true,
    ),
    onTimeChange = {
        println(it.toString())
    }
)

```

### Multiplatform Dependency

#### Step 1 : Add the Github Packages Repo

```kotlin

val githubProperties = Properties()
githubProperties.load(FileInputStream(rootProject.file("github.properties")))

allprojects {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/username/repo")
            credentials {
                username = (githubProperties["gpr.usr"] ?: System.getenv("GPR_USER")).toString()
                password = (githubProperties["gpr.key"] ?: System.getenv("GPR_API_KEY")).toString()
            }
        }
    }
}
```

#### Step 2 : Create Github Properties File

Create `github.properties` file in your project at root level and add two properties (make github personal access token)

```properties
gpr.usr=yourusername
gpr.key=yourgithubpersonalaccesstoken
```

#### Step 3 : Add The Dependency

Get the latest version , check it from releases page or better from the packages

This code is for Kotlin Gradle Script , Convert it over to groovy by removing quotes and braces if you use groovy !

```kotlin
implementation("com.wakaztahir:datetime:<current-version>")
```