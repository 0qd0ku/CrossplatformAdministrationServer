package ru.vkr.model;

import java.util.Objects;

public class TaskDataDto {
    private Long id;
    private String name;
    private String taskType;
    private String version;
    private String os;
    private String osType;
    private String pathRunFile;
    private String torrentFile;

    public TaskDataDto(Long id, String name, String taskType, String version, String os, String osType, String pathRunFile, String torrentFile) {
        this.id = id;
        this.name = name;
        this.taskType = taskType;
        this.version = version;
        this.os = os;
        this.osType = osType;
        this.pathRunFile = pathRunFile;
        this.torrentFile = torrentFile;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getOsType() {
        return osType;
    }

    public void setOsType(String osType) {
        this.osType = osType;
    }

    public String getPathRunFile() {
        return pathRunFile;
    }

    public void setPathRunFile(String pathRunFile) {
        this.pathRunFile = pathRunFile;
    }

    public String getTorrentFile() {
        return torrentFile;
    }

    public void setTorrentFile(String torrentFile) {
        this.torrentFile = torrentFile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TaskDataDto)) return false;
        TaskDataDto that = (TaskDataDto) o;
        return id.equals(that.id) &&
                getName().equals(that.getName()) &&
                getTaskType() == that.getTaskType() &&
                getVersion().equals(that.getVersion()) &&
                getOs() == that.getOs() &&
                getOsType() == that.getOsType() &&
                getPathRunFile().equals(that.getPathRunFile()) &&
                getTorrentFile().equals(that.getTorrentFile());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, getName(), getTaskType(), getVersion(), getOs(), getOsType(), getPathRunFile(), getTorrentFile());
    }

    @Override
    public String toString() {
        return "TaskDataDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", taskType=" + taskType +
                ", version='" + version + '\'' +
                ", os=" + os +
                ", osType=" + osType +
                ", pathRunFile='" + pathRunFile + '\'' +
                ", torrentFile=" + torrentFile +
                '}';
    }
}
