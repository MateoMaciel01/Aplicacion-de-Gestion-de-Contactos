# 📱 Gestión de Contactos

Aplicación Android desarrollada para administrar contactos personales de forma sencilla. Permite registrar información detallada de cada contacto, almacenarla localmente mediante SQLite y consultar posteriormente todos los registros creados.

---

# 🚀 Funcionalidades

## ➕ Registro de contactos

- Alta de nuevos contactos.
- Validación de todos los campos obligatorios.
- Verificación del formato del correo electrónico.
- Validación de nombre y apellido.
- Validación del número telefónico.
- Selección del tipo de teléfono mediante **Spinner**.
- Selección de la fecha de nacimiento.
- Navegación entre formularios para completar la información.

## 📝 Información adicional

Cada contacto puede almacenar además:

- Estado mediante **Switch**.
- Género mediante **RadioButton**.
- Intereses mediante **CheckBox**.

## 📋 Listado de contactos

La aplicación permite:

- Consultar todos los contactos registrados.
- Mostrar nombre, apellido y correo electrónico.
- Visualizar toda la información del contacto seleccionado.

---

# 🛠️ Tecnologías utilizadas

- Java
- Android Studio
- SQLite
- XML
- Material Design Components

---

# ✔ Validaciones implementadas

- Todos los campos son obligatorios.
- El nombre y apellido solo admiten letras.
- El teléfono admite únicamente números y guiones.
- Validación del formato del correo electrónico.
- Validación de la fecha de nacimiento.
- Selección obligatoria de un **RadioButton**.
- Selección obligatoria de al menos un **CheckBox** de intereses.

---

# 🗄️ Base de datos

La aplicación utiliza **SQLite** para almacenar toda la información de los contactos.

### Datos almacenados

- Nombre
- Apellido
- Fecha de nacimiento
- Teléfono
- Tipo de teléfono
- Correo electrónico
- Estado
- Género
- Intereses

---

# 📱 Características

- Navegación mediante menú superior.
- Formularios adaptables utilizando **ScrollView**.
- Persistencia local de datos mediante SQLite.
- Consulta dinámica de contactos almacenados.

---

# ⚙️ Instalación

### 1. Clonar el repositorio

```bash
git clone https://github.com/TU-USUARIO/TU-REPOSITORIO.git
```

### 2. Abrir el proyecto

Abrir el proyecto utilizando **Android Studio**.

### 3. Ejecutar la aplicación

Ejecutar la aplicación en un dispositivo físico o emulador Android.

---

# 📌 Estado del proyecto

✅ Proyecto académico finalizado.

---

# 👨‍💻 Autor

**Mateo Maciel**
