# 🤖 Offline Assistant

An AI-powered Android chatbot that runs **completely offline** using an on-device Large Language Model (LLM). Built with modern Android development practices, the application demonstrates clean architecture, local data persistence, dependency injection, and on-device AI inference.

The primary objective of this project is to demonstrate modern Android development by combining on-device AI capabilities with a scalable and maintainable application architecture.

---

# 📱 Overview

Offline Assistant enables users to interact with an AI model directly on their Android device. All conversations are processed locally, ensuring complete privacy while eliminating dependency on cloud-based AI services.

The application is built using **Kotlin**, **Jetpack Compose**, **MVVM Architecture**, **Hilt**, **Room Database**, **Kotlin Coroutines**, **Flow**, and **Google MediaPipe GenAI**.

---

# ✨ Features

- 🤖 Fully offline AI conversations
- 💬 Modern chat interface built with Jetpack Compose
- 💾 Persistent conversation history using Room Database
- 🗂️ Support for multiple conversations
- 🧠 On-device AI inference using Google MediaPipe GenAI
- 🧩 Dependency Injection with Hilt
- ⚡ Material Design 3 UI
- 🧭 Navigation using Navigation Compose
- 📱 Responsive Compose-based interface

---

## 📸 Screenshots

### Initial Setup

![Initial Setup](screenshots/01_initial_setup.png)

### Welcome Screen

![Welcome Screen](screenshots/02_welcome_screen.png)

### Empty Chat

![Empty Chat](screenshots/03_empty_chat.png)

### AI Generating Response

![AI Generating Response](screenshots/04_ai_generating_response.png)

### Chat Conversation

![Chat Conversation](screenshots/05_chat_conversation.png)

### Conversation Menu

![Conversation Menu](screenshots/06_conversation_menu.png)


---

# 🏗️ Architecture

The project follows the **MVVM (Model–View–ViewModel)** architecture along with the **Repository Pattern** to ensure separation of concerns, maintainability, and scalability.

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
│   └── manager (AI Model Management)
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
| Google MediaPipe GenAI | On-device AI Inference |

---

# 🎯 Key Highlights

- Fully offline AI chatbot with on-device inference
- Built entirely with Jetpack Compose
- Clean MVVM architecture with Repository Pattern
- Dependency Injection using Hilt
- Local conversation persistence using Room Database
- Reactive UI powered by Kotlin Coroutines and Flow
- Privacy-first design with no cloud communication

---

# ⚙️ Installation

## Clone the repository

```bash
git clone https://github.com/arjuna-b/Offline-Assistant.git
```

Open the project using **Android Studio**.

Sync the Gradle dependencies.

Build and run the application on a supported Android device or emulator.

---

# 🧠 AI Model Setup

> **Important**

The AI model is intentionally **not included** in this repository because of its large file size and distribution limitations.

To run the application:

1. Download a compatible **Google MediaPipe GenAI** supported LLM model.
2. Place the model in the application's expected storage location.
3. Build and run the application.

> **Note:** The project will compile successfully, but AI responses will not be generated unless a compatible model is available.

---

# 💾 Local Storage

Conversation data is persisted locally using **Room Database**.

Stored information includes:

- Conversations
- Chat Messages
- Conversation History

This allows users to continue previous conversations even after restarting the application.

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

All AI inference is performed locally on the device.

No prompts, conversations, or user data are transmitted to external servers.

This ensures:

- Better privacy
- Offline availability
- Reduced network dependency

---

# 🚀 Future Enhancements

- Markdown rendering
- Streaming AI responses
- Search conversations
- Export conversations
- Voice input
- Multiple AI model support
- Retrieval-Augmented Generation (RAG)
- Unit Testing
- UI Testing

---

# 🎯 Learning Outcomes

This project strengthened my understanding of:

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
- Scalable Android Application Architecture

---

# 👨‍💻 About This Project

Offline Assistant was built as a personal learning project to explore modern Android application development and on-device AI using Google's MediaPipe GenAI framework.

The focus was on designing a maintainable, scalable, privacy-focused Android application that demonstrates current Android development best practices while integrating local AI capabilities.
