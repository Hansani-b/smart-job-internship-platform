
import {
  Search,
  MapPin,
  Briefcase,
  Clock,
  Building2,
} from 'lucide-react'

function JobSearch() {
  const jobs = [
    {
      id: 1,
      title: 'Frontend Developer Intern',
      company: 'ABC Technologies',
      location: 'Colombo',
      type: 'Internship',
      salary: 'Negotiable',
    },
    {
      id: 2,
      title: 'Backend Developer Intern',
      company: 'Tech Solutions',
      location: 'Remote',
      type: 'Internship',
      salary: 'Paid',
    },
    {
      id: 3,
      title: 'Full Stack Developer',
      company: 'Innovate Lanka',
      location: 'Kandy',
      type: 'Full-time',
      salary: 'Negotiable',
    },
  ]

  return (
    <div className="min-h-screen bg-slate-100 p-6 md:p-10">

      {/* Header */}
      <div className="mb-8">
        <h1 className="text-3xl font-bold text-slate-800">
          Find Jobs
        </h1>
        <p className="text-slate-500 mt-2">
          Discover opportunities that match your skills.
        </p>
      </div>

      {/* Search Bar */}
      <div className="bg-white p-5 rounded-2xl shadow-sm mb-8">
        <div className="grid grid-cols-1 md:grid-cols-3 gap-4">

          <div className="relative">
            <Search size={20} className="absolute left-3 top-3.5 text-slate-400" />
            <input
              type="text"
              placeholder="Job title or keyword"
              className="w-full border border-slate-300 rounded-lg pl-10 pr-4 py-3"
            />
          </div>

          <div className="relative">
            <MapPin size={20} className="absolute left-3 top-3.5 text-slate-400" />
            <input
              type="text"
              placeholder="Location"
              className="w-full border border-slate-300 rounded-lg pl-10 pr-4 py-3"
            />
          </div>

          <button className="bg-blue-600 hover:bg-blue-700 text-white rounded-lg font-semibold py-3 flex items-center justify-center gap-2">
            <Search size={18} />
            Search Jobs
          </button>

        </div>
      </div>

      {/* Job Listings */}
      <div>
        <h2 className="text-xl font-bold text-slate-800 mb-5">
          Latest Opportunities
        </h2>

        <div className="space-y-5">
          {jobs.map((job) => (
            <div
              key={job.id}
              className="bg-white p-6 rounded-xl shadow-sm hover:shadow-md transition"
            >
              <div className="flex flex-col md:flex-row md:justify-between gap-5">

                <div>
                  <h3 className="text-lg font-bold text-slate-800">
                    {job.title}
                  </h3>

                  <p className="text-slate-500 mt-2 flex items-center gap-2">
                    <Building2 size={17} />
                    {job.company}
                  </p>

                  <div className="flex flex-wrap gap-4 mt-4 text-sm text-slate-500">
                    <span className="flex items-center gap-1">
                      <MapPin size={16} />
                      {job.location}
                    </span>

                    <span className="flex items-center gap-1">
                      <Briefcase size={16} />
                      {job.type}
                    </span>

                    <span className="flex items-center gap-1">
                      <Clock size={16} />
                      {job.salary}
                    </span>
                  </div>
                </div>

                <div className="flex items-center">
                  <button className="bg-blue-600 hover:bg-blue-700 text-white px-6 py-3 rounded-lg font-semibold">
                    View Details
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

export default JobSearch
