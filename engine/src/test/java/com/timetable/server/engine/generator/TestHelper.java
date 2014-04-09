package com.timetable.server.engine.generator;

import com.timetable.server.engine.model.input.ClassGroup;
import com.timetable.server.engine.model.input.SubjectClassGroup;
import com.timetable.server.engine.model.input.TeacherInfo;

public class TestHelper {
	public static ClassGroup getClassGroup1() {
		ClassGroup classGroup = new ClassGroup();
		classGroup.setClassGroupId("1A");
		classGroup.setSubjects(new String[]{"MATHS", "PHY"});
		classGroup.setTotalStudents(40);
		return classGroup;
	}

	public static ClassGroup getClassGroup2() {
		ClassGroup classGroup = new ClassGroup();
		classGroup.setClassGroupId("2B");
		classGroup.setSubjects(new String[]{"HINDI", "PHY"});
		classGroup.setTotalStudents(35);
		return classGroup;
	}

	public static TeacherInfo getTeacherInfo1() {
		TeacherInfo teacherInfo = new TeacherInfo();
		teacherInfo.setTeacherId(1);
		teacherInfo.setWeeklyTeachingHours(4);

		SubjectClassGroup group1 = new SubjectClassGroup("MATHS", "1A");
		SubjectClassGroup group2 = new SubjectClassGroup("HINDI", "2B");
		SubjectClassGroup[] subjectClassGroups = new SubjectClassGroup[]{group1, group2};

		teacherInfo.setSubjectClassGroups(subjectClassGroups);

		return teacherInfo;
	}

	public static TeacherInfo getTeacherInfo2() {
		TeacherInfo teacherInfo = new TeacherInfo();
		teacherInfo.setTeacherId(2);
		teacherInfo.setWeeklyTeachingHours(4);

		SubjectClassGroup group1 = new SubjectClassGroup("PHY", "1A");
		SubjectClassGroup group2 = new SubjectClassGroup("PHY", "2B");
		SubjectClassGroup[] subjectClassGroups = new SubjectClassGroup[]{group1, group2};

		teacherInfo.setSubjectClassGroups(subjectClassGroups);

		return teacherInfo;
	}

	public static TeacherInfo[] getTeacherInfos() {
		return new TeacherInfo[]{getTeacherInfo1(), getTeacherInfo2()};
	}

	public static ClassGroup[] getClassGroups() {
		return new ClassGroup[]{getClassGroup1(), getClassGroup2()};
	}
}
