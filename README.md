# Brain Tumor Detection Mobile App
![tumor2](https://github.com/user-attachments/assets/0a43bfeb-5d71-4ae0-ad9e-3e70a5a68030)

## Overview
The Brain Tumor Detection Mobile App is a sophisticated application designed to assist in the early detection of brain tumors using advanced image processing and machine learning techniques. The app integrates with a dedicated API to process MRI images, manage patient details, save reports, and facilitate communication with doctors, providing a comprehensive solution for both diagnosis and follow-up care.

## Features
- **Image Upload:** Upload brain MRI images for analysis.
- **Tumor Detection:** Utilize machine learning models through the integrated API to detect and classify brain tumors.
- **Patient Details Management:** Add and manage patient details associated with MRI scans.
- **Report Saving:** Save and view detailed reports of the analysis results.
- **Profile Page:** Access and manage user profile information.
- **Chat Page:** Communicate with doctors through an integrated messaging system.
- **Results Visualization:** Display detection results with visual highlights of tumor regions.
- **User-Friendly Interface:** Intuitive design for easy navigation and usage.

## Technologies Used
- **Programming Languages:** Java
- **Android Development:** Android SDK
- **Network Requests:** Retrofit
- **Database:** Firebase Realtime Database, Room Database
- **Authentication:** Firebase Authentication
- **Storage:** Firebase Storage
- **Preferences:** SharedPreferences
- **Reactive Programming:** RxJava
- **Messaging:** Firebase Realtime Database
- **Version Control:** Git , Github
- **IDE:** Android Studio
## API
app uses an api to get all scan results.
- **Link :** [FastApi](https://github.com/Abdelrahman-Kamel8886/Brain-Tumor-Detection-FastApi)

## Installation
To get started with the Brain Tumor Detection Mobile App, follow these steps:

## Prerequisites
- **Android Studio (for Android development)**
- **An Android device or emulator for testing**
- **Required dependencies and SDKs**
  
## Clone the Repository
      ```bash
      git clone https://github.com/Abdelrahman-Kamel8886/Brain-Tumor-Detection-MobileApp.git

## Setup and Configuration
1. **Open Android Studio and import the project.**
2. **Sync the project with Gradle files.**
3. **Configure the Firebase services by adding your google-services.json file to the app directory.**
4. **Set up Firebase Authentication, Realtime Database, and Storage as described in the Firebase documentation.**
5. **Set up Room Database and SharedPreferences for local data storage and management.**
6. **Integrate Retrofit for network requests and configure it to communicate with the Brain Tumor Detection FastAPI.**
7. **Integrate RxJava for reactive programming and data handling.**

## Running the App
1. **Connect your Android device or start an emulator.**
2. **Click on the "Run" button in Android Studio to build and deploy the app to your device.**

## Usage
1. **Launch the app on your device.**
2. **Navigate to the image upload section and select a brain MRI image.**
3. **Add patient details associated with the MRI scan.**
4. **The app will send the MRI image to the Brain Tumor Detection FastAPI for analysis and display the results, including any detected tumors.**
5. **Save and view detailed reports of the analysis results.**
6. **Use the profile page to manage your personal information.**
7. **Access the chat page to communicate with doctors for further consultation.**
8. **Review the results and consult with medical professionals for comprehensive analysis and advice.**

## Screenshots

### Login
![Screenshot_2022-07-17-16-41-34-48_7ec8192640c8b0907e1d92af89b9c133](https://github.com/Abdelrahman-Kamel8886/Brain-Tumor-Detection-MobileApp/assets/126878089/386ac849-9ad4-4d48-8ced-1addfc1ae6f4)

![Screenshot_2022-07-17-16-41-51-05_7ec8192640c8b0907e1d92af89b9c133](https://github.com/Abdelrahman-Kamel8886/Brain-Tumor-Detection-MobileApp/assets/126878089/54c1eb2e-b2cd-497f-96a8-ae5263f2e472)

### Sign up
![Screenshot_2022-07-17-16-42-33-53_7ec8192640c8b0907e1d92af89b9c133](https://github.com/Abdelrahman-Kamel8886/Brain-Tumor-Detection-MobileApp/assets/126878089/f433f3da-01c5-46b1-8215-684e3a9e19aa)

### Main Page
![Screenshot_2022-07-17-16-50-55-31_7ec8192640c8b0907e1d92af89b9c133](https://github.com/Abdelrahman-Kamel8886/Brain-Tumor-Detection-MobileApp/assets/126878089/d757760f-5ada-4999-a744-c40aac82b181)

### Profie
![Screenshot_2022-07-17-16-50-59-63_7ec8192640c8b0907e1d92af89b9c133](https://github.com/Abdelrahman-Kamel8886/Brain-Tumor-Detection-MobileApp/assets/126878089/f707cc14-b139-4866-9ffd-7f03b491b91d)

### Scan without saving the patient
![Screenshot_2022-07-17-16-51-06-53_7ec8192640c8b0907e1d92af89b9c133](https://github.com/Abdelrahman-Kamel8886/Brain-Tumor-Detection-MobileApp/assets/126878089/1e578f99-3c92-4a34-8092-01d7b7ce5e34)
![Screenshot_2022-07-17-16-51-20-77_7ec8192640c8b0907e1d92af89b9c133](https://github.com/Abdelrahman-Kamel8886/Brain-Tumor-Detection-MobileApp/assets/126878089/ccd34c73-d62a-4ac2-897e-adb9ef39c1dd)
![Screenshot_2022-07-17-16-51-27-59_7ec8192640c8b0907e1d92af89b9c133](https://github.com/Abdelrahman-Kamel8886/Brain-Tumor-Detection-MobileApp/assets/126878089/b62c8c50-a277-4ca5-b2e1-c1fe152d0b25)
![Screenshot_2022-07-17-16-51-32-13_7ec8192640c8b0907e1d92af89b9c133](https://github.com/Abdelrahman-Kamel8886/Brain-Tumor-Detection-MobileApp/assets/126878089/cc26482c-1298-4d81-84d1-e8ac6e74039c)

### All Patients Page
![Screenshot_2022-07-17-16-51-54-84_7ec8192640c8b0907e1d92af89b9c133](https://github.com/Abdelrahman-Kamel8886/Brain-Tumor-Detection-MobileApp/assets/126878089/0a2d7532-7122-41f4-b98c-390b69596664)

### Add New Patient
![Screenshot_2022-07-17-16-52-52-61_7ec8192640c8b0907e1d92af89b9c133](https://github.com/Abdelrahman-Kamel8886/Brain-Tumor-Detection-MobileApp/assets/126878089/b65a8967-d397-46f4-914b-00ccfb800942)
![Screenshot_2022-07-17-16-52-56-01_7ec8192640c8b0907e1d92af89b9c133](https://github.com/Abdelrahman-Kamel8886/Brain-Tumor-Detection-MobileApp/assets/126878089/1dc57c28-d70f-44f6-8191-7a1fa89fd704)
![Screenshot_2022-07-17-16-53-26-96_7ec8192640c8b0907e1d92af89b9c133](https://github.com/Abdelrahman-Kamel8886/Brain-Tumor-Detection-MobileApp/assets/126878089/5c9579c1-c259-4e00-9c54-c13ea5b00538)

### All Patients Page then
![Screenshot_2022-07-17-16-53-44-76_7ec8192640c8b0907e1d92af89b9c133](https://github.com/Abdelrahman-Kamel8886/Brain-Tumor-Detection-MobileApp/assets/126878089/e63a0eba-ea08-4d1f-844c-d7a942b5a1dc)
### Patient Page
![Screenshot_2022-07-17-16-53-51-92_7ec8192640c8b0907e1d92af89b9c133](https://github.com/Abdelrahman-Kamel8886/Brain-Tumor-Detection-MobileApp/assets/126878089/a2848198-284a-4e0f-a660-42a3ad400cc1)
![Screenshot_2022-07-17-16-53-55-74_7ec8192640c8b0907e1d92af89b9c133](https://github.com/Abdelrahman-Kamel8886/Brain-Tumor-Detection-MobileApp/assets/126878089/69d764cc-af36-4e84-a04d-57f2db7cf0c5)

### Reports
![Screenshot_2022-07-17-16-54-06-86_7ec8192640c8b0907e1d92af89b9c133](https://github.com/Abdelrahman-Kamel8886/Brain-Tumor-Detection-MobileApp/assets/126878089/218c603c-4067-4d09-a005-d38bba7a0c79)

### Patient Report Page
![Screenshot_2022-07-17-16-54-14-11_7ec8192640c8b0907e1d92af89b9c133](https://github.com/Abdelrahman-Kamel8886/Brain-Tumor-Detection-MobileApp/assets/126878089/e4817cf4-21aa-43fb-8312-b8673d39d338)

### Chat
![Screenshot_2022-07-17-17-06-15-91_7ec8192640c8b0907e1d92af89b9c133](https://github.com/Abdelrahman-Kamel8886/Brain-Tumor-Detection-MobileApp/assets/126878089/76a505cd-b717-40e5-9294-0f22293eaf4c)
![Screenshot_2022-07-17-17-07-53-62_7ec8192640c8b0907e1d92af89b9c133](https://github.com/Abdelrahman-Kamel8886/Brain-Tumor-Detection-MobileApp/assets/126878089/38cae27b-ca43-49ed-9fd1-06bffd3002e9)

## UI/UX Designed By
- ### Mostafa Hussien
   - **Figma Link: [BTD Design](https://www.figma.com/design/37s32CG2afhFUedX2iuBLw/BTD)**
   - **Contact Mostafa: [Mostafa's Linktree](https://linktr.ee/mosta7ahussi3n)**
## Contributing
Contributions are welcome! If you would like to contribute to the development of the Brain Tumor Detection Mobile App, please follow these guidelines:

1. **Fork the repository..**
2. **Create a new branch for your feature or bug fix..**
3. **Commit your changes with descriptive messages..**
4. **Push your branch to your forked repository..**
5. **Submit a pull request with a clear description of the changes.**

## Contact
For any questions or inquiries, please contact:
- ### Abdelrahman Kamel
  - **LinkedIn: [LinkedIn Profile](www.linkedin.com/in/abdelrahman-kamel-7a7457200)**
  - **Email: abdelrahmankamel8886@gmail.com**







