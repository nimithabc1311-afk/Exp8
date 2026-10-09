# Experiment 8: Implement Menus and WebView in an Android Application

**Name:** Nimitha BC  **USN:** 25MCAR0132

## 1. Aim
To develop an Android application ("Mini Browser") that demonstrates the three types of Android menus (Options Menu, Context Menu, Popup Menu) and displays web content inside the app using `WebView`.

## 2. Concepts / Technology

### Menus
| Type | How it is triggered | Key APIs |
|------|--------------------|----------|
| **Options Menu** | Three-dot icon in the app bar | `onCreateOptionsMenu()`, `onOptionsItemSelected()` |
| **Context Menu** | Long-press on a view (the blue name/USN bar) | `registerForContextMenu()`, `onCreateContextMenu()`, `onContextItemSelected()` |
| **Popup Menu** | Tap on a button, menu appears anchored to it | `PopupMenu`, `setOnMenuItemClickListener()` |

Menus are defined in XML (`res/menu/`) and inflated with `MenuInflater`.

### WebView
`WebView` is a view that renders web pages inside the app.
- `loadUrl(url)` loads a page.
- `WebViewClient` keeps links opening inside the app instead of the external browser.
- `WebChromeClient.onProgressChanged()` drives the loading progress bar.
- `canGoBack()/goBack()/goForward()/reload()` manage history.
- Needs `<uses-permission android:name="android.permission.INTERNET" />` in the manifest.

## 3. Scenario: "Mini Browser"
A simple browser app. The user:
1. Uses the **Options Menu** to open Google, Wikipedia or Android Developers, refresh the page, view About, or exit.
2. **Long-presses** the blue name/USN bar to open the **Context Menu** (Reload, Show Page Title, Clear History).
3. Taps the **Popup Menu** button for Go Back, Go Forward, and Show Current URL.
4. Sees page loading progress in a progress bar; the Back button navigates WebView history first.

## 4. Project Structure
```
MenuWebView/
├── README.md
├── screenshots/
│   ├── output.png
│   ├── testcase1.png
│   ├── testcase2.png
│   └── testcase3.png
└── app/src/main/
    ├── AndroidManifest.xml              # INTERNET permission, launcher activity
    ├── java/com/example/menuwebview/
    │   └── MainActivity.java            # WebView setup + all three menu handlers
    └── res/
        ├── layout/activity_main.xml     # Header TextView, popup Button, ProgressBar, WebView
        ├── menu/
        │   ├── main_menu.xml            # Options menu items
        │   ├── context_menu.xml         # Context menu items
        │   └── popup_menu.xml           # Popup menu items
        └── values/strings.xml           # App name, student name and USN
```

## 5. How to Run
1. Open the project in Android Studio (Giraffe or newer).
2. Edit `strings.xml` with your name and USN.
3. Start an emulator (with internet) or connect a phone, and click **Run**.

## 6. Output
<img width="720" height="1600" alt="WhatsApp Image 2026-10-09 at 11 07 40 PM" src="https://github.com/user-attachments/assets/20f6aa7a-1be9-4d51-bef5-c151e902bd22" />
<img width="720" height="1600" alt="WhatsApp Image 2026-10-09 at 11 07 41 PM (1)" src="https://github.com/user-attachments/assets/d70488b4-163e-4244-8bfd-e85d81439352" />
<img width="720" height="1600" alt="WhatsApp Image 2026-10-09 at 11 07 41 PM" src="https://github.com/user-attachments/assets/b620b9de-380b-492d-b8f8-1376ff208ea2" />




## 7. Test Cases

| # | Test Case | Steps | Expected Result |
|---|-----------|-------|-----------------|
| 1 | Options menu: load Wikipedia | Tap ⋮ → Wikipedia | Wikipedia loads inside the app |
| 2 | Context menu: show page title (shows Name & USN) | Long-press the blue Name/USN bar → Show Page Title | Toast with page title; header shows name and USN |
| 3 | Popup menu: Go Back / Show URL | Tap popup button → Show Current URL | Toast displays the current URL |



## 8. Conclusion
The experiment demonstrates Options, Context and Popup menus and an embedded WebView in a working Android application.
