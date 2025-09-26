# Business English Learning App

A modern Android application for learning Business English on the go, built with Kotlin.

## Features

- **Onboarding Screen**: Beautiful first-time user experience with professional design
- **Modern UI**: Clean, intuitive interface following Material Design principles
- **Responsive Layout**: Optimized for various screen sizes
- **Professional Branding**: Business-focused design with elegant typography

## Screenshots

The app features an onboarding screen with:
- Professional header with "Onboard" text
- Hero image of a business professional
- Page indicators for navigation
- Main title: "Learn Business English On The Go"
- Subtitle: "Track progress. Learn offline."
- Descriptive text about offline learning capabilities
- Call-to-action "Get started" button

## Technical Details

### Architecture
- **Language**: Kotlin
- **UI Framework**: Android Views with ConstraintLayout
- **Minimum SDK**: API 24 (Android 7.0)
- **Target SDK**: API 34 (Android 14)

### Project Structure
```
app/
├── src/main/
│   ├── java/com/example/businessenglish/
│   │   └── MainActivity.kt
│   ├── res/
│   │   ├── layout/
│   │   │   └── activity_main.xml
│   │   ├── values/
│   │   │   ├── colors.xml
│   │   │   └── strings.xml
│   │   └── drawable/
│   │       ├── button_background.xml
│   │       ├── indicator_active.xml
│   │       ├── indicator_inactive.xml
│   │       ├── rounded_image_background.xml
│   │       └── business_woman.xml
│   └── AndroidManifest.xml
```

### Key Components

#### MainActivity.kt
- Main entry point of the application
- Handles button click events
- Sets up the onboarding screen
- Configures status bar styling

#### Layout (activity_main.xml)
- ConstraintLayout for responsive design
- Professional typography with multiple font weights
- Proper spacing and margins for mobile UX
- Rounded corners and modern styling

#### Resources
- **Colors**: Professional color palette with grays and blue accent
- **Strings**: Localized text content for easy translation
- **Drawables**: Vector graphics and shapes for scalable UI elements

## Getting Started

### Prerequisites
- Android Studio Arctic Fox or later
- Android SDK API 24+
- Kotlin 1.8+

### Installation
1. Clone the repository
2. Open the project in Android Studio
3. Sync the project with Gradle files
4. Run the app on an emulator or physical device

### Building
```bash
./gradlew assembleDebug
```

### Running Tests
```bash
./gradlew test
```

## Design Specifications

### Colors
- Background: `#F5F5F5` (Light gray)
- Primary Text: `#333333` (Dark gray)
- Secondary Text: `#666666` (Medium gray)
- Button: `#7B9FE0` (Blue)
- White: `#FFFFFF`

### Typography
- Header: 18sp, Medium weight
- Main Title: 32sp, Light weight
- Subtitle: 20sp, Medium weight
- Description: 16sp, Regular weight
- Button: 16sp, Medium weight

### Spacing
- Screen margins: 24dp
- Element spacing: 16-40dp
- Button height: 56dp
- Button radius: 28dp

## Future Enhancements

- [ ] Add navigation to learning modules
- [ ] Implement user progress tracking
- [ ] Add offline lesson downloads
- [ ] Create user authentication
- [ ] Add multiple onboarding screens
- [ ] Implement dark mode support
- [ ] Add accessibility features
- [ ] Create tablet-optimized layouts

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests if applicable
5. Submit a pull request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Contact

For questions or support, please contact the development team.
