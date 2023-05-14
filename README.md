<div align="center">
  <img src="https://github.com/Kamil-Malik/lelenime/blob/master/core/common/src/main/res/drawable/lelenime.png" width="480" alt="Centered Image">
  
  ![AUR license](https://img.shields.io/aur/license/android-studio?style=for-the-badge)
  ![GitHub release (latest by date)](https://img.shields.io/github/v/release/Kamil-Malik/lelenime?style=for-the-badge)
  ![Libraries.io dependency status for GitHub repo](https://img.shields.io/librariesio/github/Kamil-Malik/lelenime?style=for-the-badge)
  ![GitHub commit activity](https://img.shields.io/github/commit-activity/w/Kamil-Malik/lelenime?style=for-the-badge)
</div>

Lelenime is a mobile app that serves as an Anime Index, providing anime information and details to anime fans. It utilizes the [Jikan API](https://github.com/jikan-me/jikan), which is a community API for anime fans that retrieves data from [MyAnimeList](https://myanimelist.net/).

## Features

- **Explore Screen:** Browse popular, airing, upcoming anime titles, and search for specific anime. Discover new series and get an overview of the anime world.
- **Detail Screen:** View detailed information about each anime, including synopsis, characters, and additional information. Dive deeper into the anime's.
- **Collection Screen:** Access a collection of recently viewed anime. Easily revisit your favorite titles or pick up where you left off.
- **Settings Screen:** Configure the app according to your preferences. Customize settings such as theme, language, notifications, and more.
- **Favorites:** Save your favorite anime titles for quick access and keep track of the shows you love.
- **Trending:** Stay up-to-date with the latest trends and releases in the anime community. Explore popular and trending anime series.

## Technologies Used

- **Kotlin**: Lelenime is built using the Kotlin programming language.
- **Jetpack** Compose: The app's user interface is developed using Jetpack Compose, a modern UI toolkit for Android.
- **Dagger** Hilt: Used for dependency injection, providing a modular and maintainable codebase.
- **Room**: An Android library used for local data persistence and working with an SQLite database.
- **Retrofit**: A popular HTTP client library used for handling network requests and communicating with the Jikan API.
- **Coroutines**: Kotlin Coroutines are employed for handling asynchronous operations in a concise and efficient manner.
- **DataStore Preferences**: Used for data persistence and storing user preferences in a modern and efficient way.
- **Accompanist Flow Layout**: A library used for creating responsive and dynamic layouts in Jetpack Compose.
- **Navigation Component**: Utilized for navigation management and handling transitions between screens.
- **Material Design 3**: Lelenime adheres to the Material Design guidelines and utilizes Material Design Components for consistent and visually appealing UI.
- **Logging Interceptor**: A library for intercepting and logging network requests and responses.
- **Paging 3 and Paging Compose**: Libraries used for implementing pagination in the app, enabling efficient data loading and display.
- **Firebase Performance Monitoring**: Integrated to monitor app performance and gather insights.
- **Firebase Crashlytics**: Integrated for real-time crash reporting and analysis.

## Getting Started

To get started with Lelenime, you have two options:

### Option 1: Install from the Play Store (Recommended)

1. Visit the [Lelenime page on the Play Store](https://play.google.com/store/apps/details?id=com.lelestacia.lelenime&pli=1) using your Android device.
2. Tap on the "Install" button.
3. Wait for the installation to complete.
4. Launch Lelenime from your device's app drawer.
5. Start exploring and enjoying the world of anime with Lelenime!

### Option 2: Build and Run from Source

1. Clone the repository: `git clone https://github.com/your-username/lelenime.git`
2. Open the project in your preferred IDE.
3. Build and run the app on an Android emulator or a physical device.
4. Start exploring and enjoying the world of anime with Lelenime!

Please note that the Play Store version is the recommended way to install Lelenime as it ensures you have the latest stable release and provides automatic updates. However, if you prefer to build and run the app from source, follow the instructions provided above.

## Contributing

Contributions to Lelenime are welcome! If you would like to contribute, please follow these steps:

1. Fork the repository by clicking the "Fork" button on the top right corner of the Lelenime repository page. This will create a copy of the repository under your GitHub account.
2. Clone your forked repository to your local machine: `git clone https://github.com/your-username/lelenime.git`.
3. Create a new branch for your feature or bug fix: `git checkout -b my-feature`.
4. Make your changes and test thoroughly to ensure they work as expected.
5. Commit your changes: `git commit -m 'Add some feature'`. Please provide a clear and descriptive commit message.
6. Push your branch to your forked repository: `git push origin my-feature`.
7. Open a pull request by visiting the Lelenime repository on GitHub. Click on the "New Pull Request" button.
8. In the "base repository" dropdown, select the `development` branch instead of the `master` branch. This ensures that your pull request is targeted at the appropriate branch.
9. Provide a detailed description of your changes, including the purpose and scope of the pull request. If your contribution is related to documentation improvements, please mention it specifically.
10. The project maintainers will review your pull request and provide feedback. Be prepared to make adjustments if requested and engage in the discussion around your contribution.
11. Once your pull request is approved and merged, your changes will become a part of the main Lelenime codebase.

If you are interested in helping with the documentation, feel free to contribute to the project's documentation repository. You can find the documentation repository at [link-to-documentation-repo].

Please make sure to adhere to the project's coding style, guidelines, and best practices. It's also recommended to discuss significant changes or new features in an issue before starting to work on them, as it allows for better coordination and avoids duplication of efforts.

Thank you for considering contributing to Lelenime! Your contributions help make the project better for the anime community.

## License

Lelenime is open-source and released under the [Apache License 2.0](LICENSE).
