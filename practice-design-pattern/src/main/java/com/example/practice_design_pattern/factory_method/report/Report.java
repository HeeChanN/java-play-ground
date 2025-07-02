package com.example.practice_design_pattern.factory_method.report;

import com.example.practice_design_pattern.factory_method.dto.AnalyticsInfo;
import com.example.practice_design_pattern.factory_method.exporter.ReportExporter;

import java.util.List;

abstract class Report {
    protected abstract ReportExporter createExporter();
    public abstract byte[] generate(List<AnalyticsInfo> data);
}
