
import {
  Building2,
  MapPin,
  Briefcase,
  Clock,
  CalendarDays,
  CheckCircle,
} from 'lucide-react'

function JobDetails() {
  const skills = ['React.js', 'JavaScript', 'HTML', 'CSS', 'Git']

  return (
    <div className="min-h-screen bg-slate-100 p-6 md:p-10">

      {/* Job Header */}
      <div className="bg-white rounded-2xl shadow-sm p-8 mb-6">

        <span className="bg-blue-100 text-blue-700 px-3 py-1 rounded-full text-sm font-medium">
          Internship
        </span>

        <h1 className="text-3xl font-bold text-slate-800 mt-5">
          Frontend Developer Intern
        </h1>

        <p className="text-slate-500 flex items-center gap-2 mt-3">
          <Building2 size={18} />
          ABC Technologies
        </p>

        <div className="flex flex-wrap gap-5 mt-6 text-sm text-slate-500">
          <span className="flex items-center gap-2">
            <MapPin size={17} />
            Colombo
          </span>

          <span className="flex items-center gap-2">
            <Briefcase size={17} />
            Internship
          </span>

          <span className="flex items-center gap-2">
            <Clock size={17} />
            Full-time
          </span>

          <span className="flex items-center gap-2">
            <CalendarDays size={17} />
            Posted Recently
          </span>
        </div>

        <button className="mt-8 bg-blue-600 hover:bg-blue-700 text-white px-8 py-3 rounded-lg font-semibold">
          Apply Now
        </button>

      </div>

      {/* Job Description */}
      <div className="bg-white rounded-2xl shadow-sm p-8 mb-6">
        <h2 className="text-xl font-bold text-slate-800 mb-4">
          Job Description
        </h2>

        <p className="text-slate-600 leading-7">
          We are looking for a motivated Frontend Developer Intern
          to join our development team. You will work with experienced
          developers to build responsive and user-friendly web applications.
        </p>
      </div>

      {/* Responsibilities */}
      <div className="bg-white rounded-2xl shadow-sm p-8 mb-6">
        <h2 className="text-xl font-bold text-slate-800 mb-4">
          Responsibilities
        </h2>

        <ul className="space-y-3 text-slate-600">
          <li>• Develop responsive user interfaces using React.</li>
          <li>• Write clean and maintainable JavaScript code.</li>
          <li>• Work with REST APIs.</li>
          <li>• Collaborate with the development team.</li>
        </ul>
      </div>

      {/* Required Skills */}
      <div className="bg-white rounded-2xl shadow-sm p-8">
        <h2 className="text-xl font-bold text-slate-800 mb-5">
          Required Skills
        </h2>

        <div className="flex flex-wrap gap-3">
          {skills.map((skill) => (
            <span
              key={skill}
              className="flex items-center gap-2 bg-blue-50 text-blue-700 px-4 py-2 rounded-lg text-sm font-medium"
            >
              <CheckCircle size={16} />
              {skill}
            </span>
          ))}
        </div>
      </div>

    </div>
  )
}


export default JobDetails
