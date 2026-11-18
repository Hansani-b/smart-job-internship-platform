
import { Routes, Route, Navigate } from 'react-router-dom'

import Login from './pages/auth/Login'
import Register from './pages/auth/Register'
import StudentDashboard from './pages/student/StudentDashboard.jsx'
import CompanyDashboard from './pages/company/CompanyDashboard.jsx'
import AdminDashboard from './pages/admin/AdminDashboard.jsx'
import StudentProfile from './pages/student/StudentProfile.jsx'
import JobSearch from './pages/student/JobSearch.jsx'
import JobDetails from './pages/student/JobDetailsPage.jsx'
import MyApplications from './pages/student/MyApplications.jsx'

function Home() {
  return <Navigate to="/login" replace />
}

function App() {
  return (
    <Routes>
      <Route path="/" element={<Home />} />

      <Route path="/login" element={<Login />} />
      <Route path="/register" element={<Register />} />

      <Route
        path="/student/dashboard"
        element={<StudentDashboard />}
      />

      <Route
        path="/student/profile"
        element={<StudentProfile />}
      />

      <Route
        path="/student/applications"
        element={<MyApplications />}
      />

      <Route
        path="/student/jobs"
        element={<JobSearch />}
      />

      <Route
        path="/student/jobs/:jobId"
        element={<JobDetails />}
      />

      <Route
        path="/company/dashboard"
        element={<CompanyDashboard />}
      />

      <Route
        path="/admin/dashboard"
        element={<AdminDashboard />}
      />
    </Routes>
  )
}

export default App
