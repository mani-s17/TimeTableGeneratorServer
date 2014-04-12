package com.timetable.server.engine.generator;

import com.timetable.server.engine.model.input.ClassGroup;
import com.timetable.server.engine.model.input.SubjectStandard;
import com.timetable.server.engine.model.input.TeacherInfo;

public class TestHelper {
	public static ClassGroup getClassGroup1() {
		ClassGroup classGroup = new ClassGroup();
		classGroup.setStandardId(1);
		classGroup.setClassGroupId("1A");
		classGroup.setSubjects(new String[]{"MATHS", "PHY"});
		classGroup.setTotalStudents(40);
		return classGroup;
	}

	public static ClassGroup getClassGroup2() {
		ClassGroup classGroup = new ClassGroup();
		classGroup.setStandardId(2);
		classGroup.setClassGroupId("2B");
		classGroup.setSubjects(new String[]{"HINDI", "PHY"});
		classGroup.setTotalStudents(35);
		return classGroup;
	}

	public static TeacherInfo getTeacherInfo1() {
		TeacherInfo teacherInfo = new TeacherInfo();
		teacherInfo.setTeacherId(1);
		teacherInfo.setWeeklyTeachingHours(4);

		SubjectStandard group1 = new SubjectStandard("MATHS", 1);
		SubjectStandard group2 = new SubjectStandard("HINDI", 2);
		SubjectStandard[] subjectStandards = new SubjectStandard[]{group1, group2};

		teacherInfo.setSubjectStandards(subjectStandards);

		return teacherInfo;
	}

	public static TeacherInfo getTeacherInfo2() {
		TeacherInfo teacherInfo = new TeacherInfo();
		teacherInfo.setTeacherId(2);
		teacherInfo.setWeeklyTeachingHours(4);

		SubjectStandard group1 = new SubjectStandard("PHY", 1);
		SubjectStandard group2 = new SubjectStandard("PHY", 2);
		SubjectStandard[] subjectStandards = new SubjectStandard[]{group1, group2};

		teacherInfo.setSubjectStandards(subjectStandards);

		return teacherInfo;
	}

	public static TeacherInfo[] getTeacherInfos() {
		return new TeacherInfo[]{getTeacherInfo1(), getTeacherInfo2()};
	}

	public static ClassGroup[] getClassGroups() {
		return new ClassGroup[]{getClassGroup1(), getClassGroup2()};
	}
}
