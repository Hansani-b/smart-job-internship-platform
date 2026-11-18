
import {
  Building2,
  MapPin,
  CalendarDays,
  Eye,
} from 'lucide-react'

function MyApplications() {
  const applications = [
    {
      id: 1,
      title: 'Frontend Developer Intern',
      company: 'ABC Technologies',
      location: 'Colombo',
      appliedDate: 'Nov 10, 2026',
      status: 'Pending',
    },
    {
      id: 2,
      title: 'Backend Developer Intern',
      company: 'Tech Solutions',
      location: 'Remote',
      appliedDate: 'Nov 08, 2026',
      status: 'Shortlisted',
    },
    {
      id: 3,
      title: 'Full Stack Developer',
      company: 'Innovate Lanka',
      location: 'Kandy',
      appliedDate: 'Nov 05, 2026',
      status: 'Rejected',
    },
  ]

  const statusStyles = {
    Pending: 'bg-yellow-100 text-yellow-700',
    Shortlisted: 'bg-green-100 text-green-700',
    Rejected: 'bg-red-100 text-red-700',
  }

  return (
    <div className="min-h-screen bg-slate-100 p-6 md:p-10">
      <div className="max-w-6xl mx-auto">

        <div className="mb-8">
          <h1 className="text-3xl font-bold text-slate-800">
            My Applications
          </h1>
          <p className="text-slate-500 mt-2">
            Track and manage your job applications.
          </p>
        </div>

        <div className="grid grid-cols-1 md:grid-cols-3 gap-5 mb-8">
          <div className="bg-white p-6 rounded-xl shadow-sm">
            <p className="text-slate-500 text-sm">Total Applications</p>
            <h2 className="text-3xl font-bold text-slate-800 mt-2">
              03
            </h2>
          </div>

          <div className="bg-white p-6 rounded-xl shadow-sm">
            <p className="text-slate-500 text-sm">Shortlisted</p>
            <h2 className="text-3xl font-bold text-green-600 mt-2">
              01
            </h2>
          </div>

          <div className="bg-white p-6 rounded-xl shadow-sm">
            <p className="text-slate-500 text-sm">Pending</p>
            <h2 className="text-3xl font-bold text-yellow-600 mt-2">
              01
            </h2>
          </div>
        </div>

        <div className="space-y-5">
          {applications.map((application) => (
            <div
              key={application.id}
              className="bg-white p-6 rounded-xl shadow-sm"
            >
              <div className="flex flex-col md:flex-row md:items-center md:justify-between gap-5">

                <div>
                  <h2 className="text-xl font-semibold text-slate-800">
                    {application.title}
                  </h2>

                  <div className="flex flex-wrap gap-4 mt-3 text-sm text-slate-500">
                    <span className="flex items-center gap-2">
                      <Building2 size={16} />
                      {application.company}
                    </span>

                    <span className="flex items-center gap-2">
                      <MapPin size={16} />
                      {application.location}
                    </span>

                    <span className="flex items-center gap-2">
                      <CalendarDays size={16} />
                      Applied: {application.appliedDate}
                    </span>
                  </div>
                </div>

                <div className="flex items-center gap-4">
                  <span
                    className={`px-4 py-2 rounded-full text-sm font-medium ${statusStyles[application.status]}`}
                  >
                    {application.status}
                  </span>

                  <button className="flex items-center gap-2 text-blue-600 hover:text-blue-800 font-medium">
                    <Eye size={18} />
                    View
                  </button>
                </div>

              </div>
            </div>
          ))}
        </div>

      </div>
    </div>
  )
}

export default MyApplications
