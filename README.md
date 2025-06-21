# 🔐 SmartControl

SmartControl is a mobile-to-PC bridge that unlocks your laptop using fingerprint authentication from your Android phone. It also lets you control WiFi, Bluetooth, and simulate mouse/trackpad movement — all through a secure Spring Boot backend.

---

## 🚀 Features

- 🔓 Unlock laptop with your phone's fingerprint
- 🖱️ Use phone as a trackpad for your laptop
- 📶 Toggle WiFi and Bluetooth on the laptop
- 🔐 Secure communication using JWT tokens
- 📡 Works on local network (WiFi)

---

## 🛠️ Tech Stack

| Layer       | Technology                  |
|------------|------------------------------|
| Mobile App | Flutter, Dart, local_auth     |
| Backend    | Java, Spring Boot, Maven     |
| Security   | JWT, HTTPS (optional), CORS  |
| DB         | MySQL                        |
| System     | Java Robot (for laptop actions) |

---

## 📱 App Preview (Coming soon...)

| Fingerprint Unlock | Trackpad Mode |
|--------------------|---------------|
| 📸 Screenshot 1     | 📸 Screenshot 2|

---

## 📦 Installation

### Backend (Spring Boot)
```bash
cd smartcontrol-backend
./mvnw spring-boot:run
