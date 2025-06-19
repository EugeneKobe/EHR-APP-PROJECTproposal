# EHR-APP-PROJECTproposal

# 🏥 Simple EHR System

A basic Electronic Health Record (EHR) system designed for a ward-based hospital setting. It allows doctors to manage patient admissions, discharges, and vital signs tracking.

---

## 📌 Core Features

- **Patient Management**: Admit, discharge, and track patients.
- **Vital Sign Monitoring**: Record and timestamp key health metrics.
- **Doctor Assignment**: Doctors can be linked to multiple patients.
- **Ward Overview**: Maintain a list of currently admitted patients.

---

## 🧱 Class Overview

### `Ward`
- Manages a list of **patient IDs**.
- Represents a hospital ward.

### `Patient`
- Stores personal details, **vital signs**, and **admission status**.
- Linked to a **Doctor**.

### `Doctor`
- Manages assigned **Patient** IDs.
- Stores login **credentials**.

### `VitalSigns`
- Records individual measurements:
  - Temperature
  - Blood pressure
  - SpO₂
- Each record is **timestamped**.

---

## 🔁 Class Interactions

- `Ward` contains multiple `Patient` IDs.
- `Patient` objects are assigned to a `Doctor`.
- `Patient` holds a list of `VitalSigns`.
- `Doctor` manages multiple patients.

---

## 🧠 Algorithms & Logic

### CRUD Operations
- Create, update, delete patients and vital signs.

### Validation Rules
- Ensure fields like **temperature** and **SpO₂** are within realistic ranges.
- Check that all required inputs are filled.

### State Management
- Toggle between **admitted** and **discharged** status.

### Lookups
- Find patients by **ID** or **name**.

### Timestamping
- Automatically assign the **current date/time** to each new vital sign entry using the system clock.

---

## 📊 Challenge: Time-Series Visualization

An optional challenge involves visualizing a patient's vital sign history as a **time-series graph**, helping to detect health trends and changes over time.

---

## 🛠️ Getting Started

1. **Create Classes**:
   - `Patient`, `Doctor`, `Ward`, `VitalSigns`

2. **Develop GUI** (e.g., `AppView`):
   - Buttons for admit/discharge
   - Input fields for vital signs
   - Status labels for feedback

3. **Connect Logic**:
   - Keep all logic in domain classes.
   - GUI should only handle user interaction.

---

## ✅ Best Practices

- Follow **MVC (Model-View-Controller)** pattern: Keep logic and UI separate.
- Use **System time** for logging and timestamps.
- Validate user input at every step.

---

## 📦 Future Improvements

- Add **login system** for doctors.
- Export patient data to file.
- Visual graphs using external libraries (e.g., JFreeChart).




