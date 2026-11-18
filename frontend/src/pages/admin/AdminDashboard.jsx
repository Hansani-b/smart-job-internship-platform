
import {
  LayoutDashboard,
  Users,
  Briefcase,
  Building2,
  ShieldCheck,
  Bell,
} from 'lucide-react'

function AdminDashboard() {
  const stats = [
    { title: 'Total Users', value: '250', icon: Users },
    { title: 'Companies', value: '35', icon: Building2 },
    { title: 'Job Posts', value: '120', icon: Briefcase },
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
            <Users size={20} />
            Manage Users
          </a>

          <a href="#" className="flex items-center gap-3 p-3 hover:bg-slate-800 rounded-lg">
            <Building2 size={20} />
            Companies
          </a>

          <a href="#" className="flex items-center gap-3 p-3 hover:bg-slate-800 rounded-lg">
            <Briefcase size={20} />
            Job Posts
          </a>
        </nav>
      </aside>

      {/* Main Content */}
      <main className="flex-1 p-6 md:p-10">

        {/* Header */}
        <div className="flex justify-between items-center mb-10">
          <div>
            <h2 className="text-2xl font-bold text-slate-800">
              Admin Dashboard
            </h2>
            <p className="text-slate-500 mt-1">
              Monitor and manage your platform.
            </p>
          </div>

          <button className="p-3 bg-white rounded-full shadow">
            <Bell size={20} className="text-slate-600" />
          </button>
        </div>

        {/* Welcome Card */}
        <div className="bg-slate-900 rounded-2xl p-8 text-white mb-8">
          <div className="flex items-center gap-3 mb-3">
            <ShieldCheck size={30} className="text-blue-400" />

            <h3 className="text-2xl font-bold">
              Platform Administration
            </h3>
          </div>

          <p className="text-slate-300">
            Manage users, companies and job opportunities from one place.
          </p>
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

export default AdminDashboard
