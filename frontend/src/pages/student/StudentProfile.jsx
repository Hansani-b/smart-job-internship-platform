
import {
  User,
  Mail,
  Phone,
  MapPin,
  GraduationCap,
  Edit,
  Save,
} from 'lucide-react'

function StudentProfile() {
  return (
    <div className="min-h-screen bg-slate-100 p-6 md:p-10">

      {/* Header */}
      <div className="mb-8">
        <h1 className="text-3xl font-bold text-slate-800">
          My Profile
        </h1>
        <p className="text-slate-500 mt-2">
          Manage your personal and academic information.
        </p>
      </div>

      {/* Profile Card */}
      <div className="bg-white rounded-2xl shadow-sm p-8 max-w-4xl">

        <div className="flex items-center gap-5 mb-8">
          <div className="w-20 h-20 rounded-full bg-blue-100 flex items-center justify-center">
            <User size={40} className="text-blue-600" />
          </div>

          <div>
            <h2 className="text-xl font-bold text-slate-800">
              Student Name
            </h2>
            <p className="text-slate-500">
              IT Undergraduate
            </p>
          </div>
        </div>

        {/* Personal Information */}
        <h3 className="text-lg font-semibold text-slate-800 mb-5">
          Personal Information
        </h3>

        <div className="grid grid-cols-1 md:grid-cols-2 gap-5">

          <div>
            <label className="block text-sm font-medium text-slate-700 mb-2">
              Full Name
            </label>
            <div className="relative">
              <User size={18} className="absolute left-3 top-3.5 text-slate-400" />
              <input
                type="text"
                placeholder="Enter your full name"
                className="w-full border border-slate-300 rounded-lg pl-10 pr-4 py-3"
              />
            </div>
          </div>

          <div>
            <label className="block text-sm font-medium text-slate-700 mb-2">
              Email Address
            </label>
            <div className="relative">
              <Mail size={18} className="absolute left-3 top-3.5 text-slate-400" />
              <input
                type="email"
                placeholder="Enter your email"
                className="w-full border border-slate-300 rounded-lg pl-10 pr-4 py-3"
              />
            </div>
          </div>

          <div>
            <label className="block text-sm font-medium text-slate-700 mb-2">
              Phone Number
            </label>
            <div className="relative">
              <Phone size={18} className="absolute left-3 top-3.5 text-slate-400" />
              <input
                type="tel"
                placeholder="Enter your phone number"
                className="w-full border border-slate-300 rounded-lg pl-10 pr-4 py-3"
              />
            </div>
          </div>

          <div>
            <label className="block text-sm font-medium text-slate-700 mb-2">
              Location
            </label>
            <div className="relative">
              <MapPin size={18} className="absolute left-3 top-3.5 text-slate-400" />
              <input
                type="text"
                placeholder="Enter your location"
                className="w-full border border-slate-300 rounded-lg pl-10 pr-4 py-3"
              />
            </div>
          </div>

        </div>

        {/* Education */}
        <h3 className="text-lg font-semibold text-slate-800 mt-10 mb-5">
          Education Information
        </h3>

        <div>
          <label className="block text-sm font-medium text-slate-700 mb-2">
            Degree / Qualification
          </label>

          <div className="relative">
            <GraduationCap size={18} className="absolute left-3 top-3.5 text-slate-400" />
            <input
              type="text"
              placeholder="Enter your degree"
              className="w-full border border-slate-300 rounded-lg pl-10 pr-4 py-3"
            />
          </div>
        </div>

        {/* Save Button */}
        <div className="flex justify-end mt-8">
          <button className="flex items-center gap-2 bg-blue-600 hover:bg-blue-700 text-white px-6 py-3 rounded-lg font-semibold">
            <Save size={18} />
            Save Profile
          </button>
        </div>

      </div>
    </div>
  )
}

export default StudentProfile
