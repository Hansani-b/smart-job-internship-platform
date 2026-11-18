
import {
  LayoutDashboard,
  Briefcase,
  Users,
  CalendarDays,
  PlusCircle,
  Bell,
} from 'lucide-react'

function CompanyDashboard() {
  const stats = [
    { title: 'Posted Jobs', value: '12', icon: Briefcase },
    { title: 'Applications', value: '48', icon: Users },
    { title: 'Interviews', value: '06', icon: CalendarDays },
  ]

  return (
    <div className="min-h-screen bg-slate-100 flex">

      {/* Sidebar */}
      <aside className="w-64 bg-slate-900 text-white p-6 hidden md:block">
        <h1 className="text-xl font-bold mb-10">
          Smart<span className="text-blue-400">Jobs</span>
        </h1>

        <nav className="space-y-4">
          <a href="#" className="flex items-center gap-3 bg-blue-600 p-3 rounded-lg">
            <LayoutDashboard size={20} />
            Dashboard
          </a>

          <a href="#" className="flex items-center gap-3 p-3 hover:bg-slate-800 rounded-lg">
            <Briefcase size={20} />
            My Jobs
          </a>

          <a href="#" className="flex items-center gap-3 p-3 hover:bg-slate-800 rounded-lg">
            <Users size={20} />
            Applications
          </a>

          <a href="#" className="flex items-center gap-3 p-3 hover:bg-slate-800 rounded-lg">
            <CalendarDays size={20} />
            Interviews
          </a>
        </nav>
      </aside>

      {/* Main Content */}
      <main className="flex-1 p-6 md:p-10">

        {/* Header */}
        <div className="flex justify-between items-center mb-10">
          <div>
            <h2 className="text-2xl font-bold text-slate-800">
              Company Dashboard
            </h2>
            <p className="text-slate-500 mt-1">
              Manage your recruitment activities.
            </p>
          </div>

          <button className="p-3 bg-white rounded-full shadow">
            <Bell size={20} className="text-slate-600" />
          </button>
        </div>

        {/* Welcome Card */}
        <div className="bg-blue-600 rounded-2xl p-8 text-white mb-8">
          <h3 className="text-2xl font-bold mb-2">
            Welcome to Your Company Portal!
          </h3>
          <p className="text-blue-100">
            Find talented candidates and manage your job opportunities.
          </p>

          <button className="mt-5 flex items-center gap-2 bg-white text-blue-600 px-5 py-3 rounded-lg font-semibold hover:bg-blue-50">
            <PlusCircle size={20} />
            Post a New Job
          </button>
        </div>

        {/* Statistics */}
        <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6">
          {stats.map((stat) => {
            const Icon = stat.icon

            return (
              <div key={stat.title} className="bg-white p-6 rounded-xl shadow-sm">
                <div className="flex justify-between items-center">
                  <div>
                    <p className="text-slate-500 text-sm">{stat.title}</p>
                    <h3 className="text-3xl font-bold text-slate-800 mt-2">
                      {stat.value}
                    </h3>
                  </div>

                  <div className="bg-blue-100 p-3 rounded-lg">
                    <Icon className="text-blue-600" size={24} />
                  </div>
                </div>
              </div>
            )
          })}
        </div>

      </main>
    </div>
  )
}

export default CompanyDashboard
