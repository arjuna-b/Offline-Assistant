# 🤖 Offline Assistant

An AI-powered Android chatbot that runs **completely offline** using an on-device Large Language Model (LLM). Built with modern Android development practices, the application demonstrates a clean architecture, local data persistence, dependency injection, and on-device AI inference.

The primary goal of this project is to showcase modern Android development skills while delivering a privacy-focused AI assistant that works without requiring an internet connection.

---

## 📱 Overview

Offline Assistant allows users to interact with an AI model directly on their Android device. All conversations are processed locally, ensuring user privacy and eliminating dependency on cloud-based AI services.

This project was developed using **Kotlin**, **Jetpack Compose**, **MVVM Architecture**, **Hilt**, **Room Database**, and **Google MediaPipe GenAI**.

---

# ✨ Features

- 🤖 Fully offline AI conversations
- 💬 Interactive chat interface built with Jetpack Compose
- 💾 Conversation history stored locally using Room Database
- 🗂️ Multiple conversation support
- 🧠 On-device AI inference using Google MediaPipe GenAI
- ⚡ Modern Material 3 UI
- 🧩 Dependency Injection using Hilt
- 🧭 Navigation using Navigation Compose
- 📱 Responsive Compose-based interface

---

# 📸 Screenshots

> Screenshots will be added soon.

| Home | Chat | Conversations |
|------|------|---------------|
| Screenshot | Screenshot | Screenshot |

---

# 🎥 Demo

> Demo video/GIF will be added soon.

---

# 🏗️ Architecture

The project follows the **MVVM (Model–View–ViewModel)** architecture along with the **Repository Pattern** to ensure separation of concerns and maintainable code.

```
                 User
                  │
                  ▼
        Jetpack Compose UI
                  │
                  ▼
             ViewModel
                  │
                  ▼
             Repository
          ┌────────┴────────┐
          │                 │
          ▼                 ▼
    Room Database     MediaPipe GenAI
          │                 │
          └────────┬────────┘
                   ▼
              Updated UI
```

---

# 📂 Project Structure

```
app
│
├── data
│   ├── dao
│   ├── database
│   ├── entity
│   ├── repository
│   └── manager
│
├── di
│   └── Hilt Modules
│
├── ui
│   ├── screens
│   ├── components
│   ├── navigation
│   ├── viewmodel
│   └── theme
│
├── MainActivity
│
└── assets
```

---

# 🛠️ Tech Stack

- Kotlin
- Jetpack Compose
- Material Design 3
- MVVM Architecture
- Repository Pattern
- Hilt Dependency Injection
- Room Database
- Kotlin Coroutines
- Kotlin Flow
- Navigation Compose
- Google MediaPipe GenAI
- Android SDK

---

# 📚 Libraries Used

| Library | Purpose |
|----------|---------|
| Jetpack Compose | Modern Android UI |
| Material 3 | UI Components |
| Room | Local Database |
| Hilt | Dependency Injection |
| Navigation Compose | Screen Navigation |
| Kotlin Coroutines | Asynchronous Programming |
| Kotlin Flow | Reactive Data Streams |
| MediaPipe GenAI | On-device AI Inference |

---

# ⚙️ Installation

## Clone the repository

```bash
git clone https://github.com/your-username/offline-assistant.git
```

Open the project using **Android Studio**.

Sync Gradle dependencies.

Run the application on a physical device or emulator.

---

# 🧠 AI Model Setup

The AI model is **not included** in this repository because of its large file size.

To run the application:

1. Download a compatible MediaPipe-supported LLM model.
2. Place the model inside the location expected by the application.
3. Rebuild and run the project.

---

# 💾 Local Storage

Conversation data is persisted using **Room Database**.

Stored data includes:

- Conversations
- Chat Messages
- Conversation History

This enables users to continue previous conversations even after restarting the application.

---

# 🔄 Application Flow

```
User enters prompt
        │
        ▼
Compose UI
        │
        ▼
ViewModel
        │
        ▼
Repository
        │
        ├────────► Local AI Model
        │              │
        │              ▼
        │       AI Response Generated
        │
        ▼
Store Conversation in Room
        │
        ▼
Update Compose UI
```

---

# 🔒 Privacy

All AI processing is performed locally on the device.

No conversations or prompts are transmitted to external servers.

This ensures:

- Better privacy
- Offline availability
- Reduced network dependency

---

# 🚀 Future Enhancements

- Markdown rendering
- Streaming AI responses
- Export conversations
- Delete conversations
- Search conversations
- Voice input
- Voice output
- Dark theme customization
- Multiple AI model support
- Image understanding
- PDF document chat
- RAG (Retrieval-Augmented Generation)
- Unit Testing
- UI Testing

---

# 🎯 Learning Outcomes

This project helped strengthen my understanding of:

- Modern Android Development
- Jetpack Compose
- MVVM Architecture
- Repository Pattern
- Dependency Injection with Hilt
- Room Database
- Kotlin Coroutines
- Kotlin Flow
- Local AI Integration
- State Management in Compose
- Android Application Architecture

---

# 👨‍💻 About This Project

This project was built as a personal learning initiative to explore modern Android application architecture and on-device AI capabilities using Google's MediaPipe GenAI framework.

The focus was on building a maintainable, scalable, and fully offline Android application using current Android development best practices.

---

# 📄 License

This project is intended for educational and portfolio purposes.
