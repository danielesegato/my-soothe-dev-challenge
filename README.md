# Compose #AndroidDevChallenge week 3 (EMEA - MySoothe)

![Workflow result](https://github.com/danielesegato/my-soothe-dev-challenge/workflows/Check/badge.svg)

This repository is my first try at Jetpack Compose, I'm not actually partecipating to the [#AndroidDevChallenge](https://developer.android.com/dev-challenge).

I'll keep doing this on weekends or when I feel like spending some time on it, just for fun.


Refer to the 3rd week of compose challenge, you can find it here: https://android-developers.googleblog.com/2021/03/android-dev-challenge-3.html.

I started from [this template](https://github.com/android/android-dev-challenge-compose) which when I generated the project was [this commit](https://github.com/android/android-dev-challenge-compose/commit/da9da9d06e1724a754a696116819d71b3b26032a).

## Code formatting
The CI uses [Spotless](https://github.com/diffplug/spotless) to check if your code is formatted correctly and contains the right licenses.
Internally, Spotless uses [ktlint](https://github.com/pinterest/ktlint) to check the formatting of your code.
To set up ktlint correctly with Android Studio, follow one of the [listed setup options](https://github.com/pinterest/ktlint#-with-intellij-idea).

Before committing your code, run `./gradlew app:spotlessApply` to automatically format your code.

## Screenshots

Welcome screen:

<img src="/results/screenshot_1_welcome_light.png" width="260">&emsp;<img src="/results/screenshot_2_welcome_dark.png" width="260">


Login screen:

<img src="/results/screenshot_3_login_light.png" width="260">&emsp;<img src="/results/screenshot_4_login_dark.png" width="260">


Home screen:

<img src="/results/screenshot_5_home_light.png" width="260">&emsp;<img src="/results/screenshot_6_home_dark.png" width="260">


## License
```
Copyright 2020 The Android Open Source Project

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    https://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```