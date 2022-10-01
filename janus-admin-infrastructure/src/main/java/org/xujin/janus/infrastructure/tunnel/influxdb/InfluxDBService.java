package org.xujin.janus.infrastructure.tunnel.influxdb;

import org.xujin.halo.infludb.InfluxDBProperties;
import org.xujin.halo.infludb.InfluxDBTemplate;
import org.xujin.janus.infrastructure.tunnel.db.influxdb.KeyValueCO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class InfluxDBService {
    @Autowired
    private InfluxDBProperties influxDBProperties;

    @Autowired
    private InfluxDBTemplate<Point> influxDBTemplate;

    public void queryJVM() {
        influxDBTemplate.createDatabase();
        final Point p = Point.measurement("disk")
                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                .tag("tenant", "default")
                .addField("used", 80L)
                .addField("free", 1L)
                .build();
        influxDBTemplate.write(p);
    }

    /**
     * 查询请求接收数量(QPS)
     *
     * @param clusterCode
     * @return
     */
    public Map<String, Object> queryRequestReceive(String clusterCode) {
        String queryRequestReceive =
                "SELECT cluster_name, env, host, value, url " +
                        "FROM request_received " +
                        "WHERE (cluster_name = '" + clusterCode + "') " +
                        "GROUP BY cluster_name, host";
        // AND time >= now() - 6h
        Query query = new Query(queryRequestReceive, influxDBProperties.getDatabase());
        QueryResult queryResult = influxDBTemplate.query(query);
        return resultToMap(queryResult);
    }

    public Map<String, Object> queryRequestReceive(String clusterCode,String host) {
        String queryRequestReceive =
                "SELECT cluster_name, env, host, value, url " +
                        "FROM request_received " +
                        "WHERE (cluster_name = '" + clusterCode + "' and host='" + host + "') ";
        // AND time >= now() - 6h
        Query query = new Query(queryRequestReceive, influxDBProperties.getDatabase());
        QueryResult queryResult = influxDBTemplate.query(query);
        return resultToMap(queryResult);
    }

    /**
     * 查询集群路由数量
     *
     * @param clusterCode
     * @return
     */
    public Map<String, Object> queryRouteCount(String clusterCode,String host) {
        String queryRequestReceive =
                "SELECT cluster_name, env, host, value " +
                        "FROM route_count " +
                        "WHERE (cluster_name = '" + clusterCode + "' and host='" + host + "') ";
        // AND time >= now() - 6h
        Query query = new Query(queryRequestReceive, influxDBProperties.getDatabase());
        QueryResult queryResult = influxDBTemplate.query(query);
        return resultToMap(queryResult);
    }

    /**
     * 查询集群请求错误数量
     *
     * @param clusterCode
     * @param host
     * @return
     */
    public Map<String, Object> queryRequestError(String clusterCode,String host) {
        String queryRequestReceive =
                "SELECT cluster_name, env, host, host,value " +
                        "FROM request_error " +
                        "WHERE (cluster_name = '" + clusterCode + "' and host='" + host + "') ";
        // AND time >= now() - 6h
        Query query = new Query(queryRequestReceive, influxDBProperties.getDatabase());
        QueryResult queryResult = influxDBTemplate.query(query);
        return resultToMap(queryResult);
    }

    /**
     * 查询磁盘空闲数量
     *
     * @param clusterCode
     * @return
     */
    public Map<String, Object> queryDiskFree(String clusterCode) {
        String queryRequestReceive =
                "SELECT cluster_name, env, host,value " +
                        "FROM disk_free " +
                        "WHERE (cluster_name = '" + clusterCode + "') " +
                        "GROUP BY cluster_name, host";
        // AND time >= now() - 6h
        Query query = new Query(queryRequestReceive, influxDBProperties.getDatabase());
        QueryResult queryResult = influxDBTemplate.query(query);
        return resultToMap(queryResult);
    }

    /**
     * 查询磁盘总量
     *
     * @param clusterCode
     * @return
     */
    public Map<String, Object> queryDiskTotal(String clusterCode) {
        String queryRequestReceive =
                "SELECT cluster_name, env, host,value " +
                        "FROM disk_total " +
                        "WHERE (cluster_name = '" + clusterCode + "') " +
                        "GROUP BY cluster_name, host";
        // AND time >= now() - 6h
        Query query = new Query(queryRequestReceive, influxDBProperties.getDatabase());
        QueryResult queryResult = influxDBTemplate.query(query);
        return resultToMap(queryResult);
    }

    /**
     * 查询Jvm内存使用数量
     *
     * @param clusterCode
     * @return
     */
    public Map<String, Object> queryJvmMemoryUsed(String clusterCode, String host) {
        String queryRequestReceive =
                "SELECT cluster_name, env, host,value " +
                        "FROM jvm_memory_used " +
                        "WHERE (cluster_name = '" + clusterCode + "' and host='" + host + "') order by time desc limit 1 ";

        // AND time >= now() - 6h
        Query query = new Query(queryRequestReceive, influxDBProperties.getDatabase());
        QueryResult queryResult = influxDBTemplate.query(query);
        return resultToJVMMap(queryResult);
    }

    /**
     * 查询jvm内存最大值
     *
     * @param clusterCode
     * @return
     */
    public Map<String, Object> queryJvmMemoryMax(String clusterCode,String host) {
        String queryRequestReceive =
                "SELECT cluster_name, env, host,value " +
                        "FROM jvm_memory_max " +
                        "WHERE (cluster_name = '" + clusterCode + "' and host='" + host + "') order by time desc limit 1";
        // AND time >= now() - 6h
        Query query = new Query(queryRequestReceive, influxDBProperties.getDatabase());
        QueryResult queryResult = influxDBTemplate.query(query);
        return resultToJVMGBMap(queryResult);
    }

    /**
     * 查询文件处理最大值
     *
     * @param clusterCode
     * @return
     */
    public Map<String, Object> queryProcessFilesMax(String clusterCode) {
        String queryRequestReceive =
                "SELECT cluster_name, env, host,value " +
                        "FROM process_files_max " +
                        "WHERE (cluster_name = '" + clusterCode + "') " +
                        "GROUP BY cluster_name, host";
        // AND time >= now() - 6h
        Query query = new Query(queryRequestReceive, influxDBProperties.getDatabase());
        QueryResult queryResult = influxDBTemplate.query(query);
        return resultToMap(queryResult);
    }

    /**
     * 查询打开处理文件符的数量
     *
     * @param clusterCode
     * @return
     */
    public Map<String, Object> queryProcessFilesOpen(String clusterCode) {
        String queryRequestReceive =
                "SELECT cluster_name, env, host,value " +
                        "FROM process_files_open " +
                        "WHERE (cluster_name = '" + clusterCode + "') " +
                        "GROUP BY cluster_name, host";
        // AND time >= now() - 6h
        Query query = new Query(queryRequestReceive, influxDBProperties.getDatabase());
        QueryResult queryResult = influxDBTemplate.query(query);
        return resultToMap(queryResult);
    }

    /**
     * 查询系统cpu数量
     *
     * @param clusterCode
     * @return
     */
    public Map<String, Object> querySystemCpuCount(String clusterCode) {
        String queryRequestReceive =
                "SELECT cluster_name, env, host,value " +
                        "FROM system_cpu_count " +
                        "WHERE (cluster_name = '" + clusterCode + "') " +
                        "GROUP BY cluster_name, host";
        // AND time >= now() - 6h
        Query query = new Query(queryRequestReceive, influxDBProperties.getDatabase());
        QueryResult queryResult = influxDBTemplate.query(query);
        return resultToMap(queryResult);
    }

    /**
     * 查询系统cpu使用情况
     *
     * @param clusterCode
     * @return
     */
    public Map<String, Object> querySystemCpuUsage(String clusterCode) {
        String queryRequestReceive =
                "SELECT cluster_name, env, host,value " +
                        "FROM system_cpu_usage " +
                        "WHERE (cluster_name = '" + clusterCode + "') " +
                        "GROUP BY cluster_name, host";
        // AND time >= now() - 6h
        Query query = new Query(queryRequestReceive, influxDBProperties.getDatabase());
        QueryResult queryResult = influxDBTemplate.query(query);
        return resultToMap(queryResult);
    }

    /**
     * 查询熔断失败速率
     *
     * @param clusterCode
     * @return
     */
    public Map<String, Object> queryBreakerFailRate(String clusterCode) {
        String queryRequestReceive =
                "SELECT cluster_name, env, host,value " +
                        "FROM breaker_fail_rate " +
                        "WHERE (cluster_name = '" + clusterCode + "') " +
                        "GROUP BY cluster_name, host";
        // AND time >= now() - 6h
        Query query = new Query(queryRequestReceive, influxDBProperties.getDatabase());
        QueryResult queryResult = influxDBTemplate.query(query);
        return resultToMap(queryResult);
    }

    /**
     * 查询慢熔断速率
     *
     * @param clusterCode
     * @return
     */
    public Map<String, Object> queryBreakerSlowRate(String clusterCode) {
        String queryRequestReceive =
                "SELECT cluster_name, env, host,value " +
                        "FROM breaker_slow_rate " +
                        "WHERE (cluster_name = '" + clusterCode + "') " +
                        "GROUP BY cluster_name, host";
        // AND time >= now() - 6h
        Query query = new Query(queryRequestReceive, influxDBProperties.getDatabase());
        QueryResult queryResult = influxDBTemplate.query(query);
        return resultToMap(queryResult);
    }

    /**
     * 查询当前连接数量
     *
     * @param clusterCode
     * @return
     */
    public Map<String, Object> queryConcurrentConnections(String clusterCode) {
        String queryRequestReceive =
                "SELECT cluster_name, env, host,value " +
                        "FROM concurrent_connections " +
                        "WHERE (cluster_name = '" + clusterCode + "') " +
                        "GROUP BY cluster_name, host";
        // AND time >= now() - 6h
        Query query = new Query(queryRequestReceive, influxDBProperties.getDatabase());
        QueryResult queryResult = influxDBTemplate.query(query);
        return resultToMap(queryResult);
    }

    /**
     * 查询连接总数
     *
     * @param clusterCode
     * @return
     */
    public Map<String, Object> queryConnections(String clusterCode) {
        String queryRequestReceive =
                "SELECT cluster_name, env, host,value " +
                        "FROM connections " +
                        "WHERE (cluster_name = '" + clusterCode + "') " +
                        "GROUP BY cluster_name, host";
        // AND time >= now() - 6h
        Query query = new Query(queryRequestReceive, influxDBProperties.getDatabase());
        QueryResult queryResult = influxDBTemplate.query(query);
        return resultToMap(queryResult);
    }

    /**
     * 查询JVM缓冲区数量
     *
     * @param clusterCode
     * @return
     */
    public Map<String, Object> queryJvmBufferCount(String clusterCode) {
        String queryRequestReceive =
                "SELECT cluster_name, env, host,value " +
                        "FROM jvm_buffer_count " +
                        "WHERE (cluster_name = '" + clusterCode + "') " +
                        "GROUP BY cluster_name, host";
        // AND time >= now() - 6h
        Query query = new Query(queryRequestReceive, influxDBProperties.getDatabase());
        QueryResult queryResult = influxDBTemplate.query(query);
        return resultToMap(queryResult);
    }

    /**
     * 查询JVM缓冲区内存使用情况
     *
     * @param clusterCode
     * @return
     */
    public Map<String, Object> queryJvmBufferMemoryUsed(String clusterCode) {
        String queryRequestReceive =
                "SELECT cluster_name, env, host,value " +
                        "FROM jvm_buffer_memory_used " +
                        "WHERE (cluster_name = '" + clusterCode + "') " +
                        "GROUP BY cluster_name, host";
        // AND time >= now() - 6h
        Query query = new Query(queryRequestReceive, influxDBProperties.getDatabase());
        QueryResult queryResult = influxDBTemplate.query(query);
        return resultToMap(queryResult);
    }

    /**
     * 查询JVM缓冲区总容量
     *
     * @param clusterCode
     * @return
     */
    public Map<String, Object> queryJvmBufferTotalCapacity(String clusterCode) {
        String queryRequestReceive =
                "SELECT cluster_name, env, host,value " +
                        "FROM jvm_buffer_total_capacity " +
                        "WHERE (cluster_name = '" + clusterCode + "') " +
                        "GROUP BY cluster_name, host";
        // AND time >= now() - 6h
        Query query = new Query(queryRequestReceive, influxDBProperties.getDatabase());
        QueryResult queryResult = influxDBTemplate.query(query);
        return resultToMap(queryResult);
    }

    /**
     * 查询JVM类加载数量(加载完毕的)
     *
     * @param clusterCode
     * @return
     */
    public Map<String, Object> queryJvmClassesLoaded(String clusterCode) {
        String queryRequestReceive =
                "SELECT cluster_name, env, host,value " +
                        "FROM jvm_classes_loaded " +
                        "WHERE (cluster_name = '" + clusterCode + "') " +
                        "GROUP BY cluster_name, host";
        // AND time >= now() - 6h
        Query query = new Query(queryRequestReceive, influxDBProperties.getDatabase());
        QueryResult queryResult = influxDBTemplate.query(query);
        return resultToMap(queryResult);
    }

    /**
     * 查询JVM类加载数量(未加载的)
     *
     * @param clusterCode
     * @return
     */
    public Map<String, Object> queryJvmClassesUnloaded(String clusterCode) {
        String queryRequestReceive =
                "SELECT cluster_name, env, host,value " +
                        "FROM jvm_classes_unloaded " +
                        "WHERE (cluster_name = '" + clusterCode + "') " +
                        "GROUP BY cluster_name, host";
        // AND time >= now() - 6h
        Query query = new Query(queryRequestReceive, influxDBProperties.getDatabase());
        QueryResult queryResult = influxDBTemplate.query(query);
        return resultToMap(queryResult);
    }

    /**
     * 查询JVM GC活跃数据大小
     *
     * @param clusterCode
     * @return
     */
    public Map<String, Object> queryJvmGCLiveDataSize(String clusterCode) {
        String queryRequestReceive =
                "SELECT cluster_name, env, host,value " +
                        "FROM jvm_gc_live_data_size " +
                        "WHERE (cluster_name = '" + clusterCode + "') " +
                        "GROUP BY cluster_name, host";
        // AND time >= now() - 6h
        Query query = new Query(queryRequestReceive, influxDBProperties.getDatabase());
        QueryResult queryResult = influxDBTemplate.query(query);
        return resultToMap(queryResult);
    }

    /**
     * 查询JVM GC最大数据大小
     *
     * @param clusterCode
     * @return
     */
    public Map<String, Object> queryJvmGCMaxDataSize(String clusterCode) {
        String queryRequestReceive =
                "SELECT cluster_name, env, host,value " +
                        "FROM jvm_gc_max_data_size " +
                        "WHERE (cluster_name = '" + clusterCode + "') " +
                        "GROUP BY cluster_name, host";
        // AND time >= now() - 6h
        Query query = new Query(queryRequestReceive, influxDBProperties.getDatabase());
        QueryResult queryResult = influxDBTemplate.query(query);
        return resultToMap(queryResult);
    }

    /**
     * 查询JVM GC内存已分配数量
     *
     * @param clusterCode
     * @return
     */
    public Map<String, Object> queryJvmGCMemoryAllocated(String clusterCode) {
        String queryRequestReceive =
                "SELECT cluster_name, env, host,value " +
                        "FROM jvm_gc_memory_allocated " +
                        "WHERE (cluster_name = '" + clusterCode + "') " +
                        "GROUP BY cluster_name, host";
        // AND time >= now() - 6h
        Query query = new Query(queryRequestReceive, influxDBProperties.getDatabase());
        QueryResult queryResult = influxDBTemplate.query(query);
        return resultToMap(queryResult);
    }

    /**
     * 查询JVM GC内存增长数量
     *
     * @param clusterCode
     * @return
     */
    public Map<String, Object> queryJvmGCMemoryPromoted(String clusterCode) {
        String queryRequestReceive =
                "SELECT cluster_name, env, host,value " +
                        "FROM jvm_gc_memory_promoted " +
                        "WHERE (cluster_name = '" + clusterCode + "') " +
                        "GROUP BY cluster_name, host";
        // AND time >= now() - 6h
        Query query = new Query(queryRequestReceive, influxDBProperties.getDatabase());
        QueryResult queryResult = influxDBTemplate.query(query);
        return resultToMap(queryResult);
    }

    /**
     * 查询JVM GC内存停顿数量
     *
     * @param clusterCode
     * @return
     */
    public Map<String, Object> queryJvmGCMemoryPause(String clusterCode) {
        String queryRequestReceive =
                "SELECT cluster_name, env, host,value " +
                        "FROM jvm_gc_pause " +
                        "WHERE (cluster_name = '" + clusterCode + "') " +
                        "GROUP BY cluster_name, host";
        // AND time >= now() - 6h
        Query query = new Query(queryRequestReceive, influxDBProperties.getDatabase());
        QueryResult queryResult = influxDBTemplate.query(query);
        return resultToMap(queryResult);
    }

    /**
     * 查询JVM 内存已提交数量
     *
     * @param clusterCode
     * @return
     */
    public Map<String, Object> queryJvmMemoryCommitted(String clusterCode,String host) {
        String queryRequestReceive =
                "SELECT cluster_name, env, host,value " +
                        "FROM jvm_memory_committed " +
                        "WHERE (cluster_name = '" + clusterCode + "' and host='" + host + "') order by time desc limit 1";
        // AND time >= now() - 6h
        Query query = new Query(queryRequestReceive, influxDBProperties.getDatabase());
        QueryResult queryResult = influxDBTemplate.query(query);
        return resultToJVMMap(queryResult);
    }

    /**
     * 查询JVM 守护线程数量
     *
     * @param clusterCode
     * @return
     */
    public Map<String, Object> queryJvmThreadsDaemon(String clusterCode) {
        String queryRequestReceive =
                "SELECT cluster_name, env, host,value " +
                        "FROM jvm_threads_daemon " +
                        "WHERE (cluster_name = '" + clusterCode + "') " +
                        "GROUP BY cluster_name, host";
        // AND time >= now() - 6h
        Query query = new Query(queryRequestReceive, influxDBProperties.getDatabase());
        QueryResult queryResult = influxDBTemplate.query(query);
        return resultToMap(queryResult);
    }


    /**
     * 查询JVM 线程活跃数
     *
     * @param clusterCode
     * @return
     */
    public Map<String, Object> queryJvmThreadsLive(String clusterCode) {
        String queryRequestReceive =
                "SELECT cluster_name, env, host,value " +
                        "FROM jvm_threads_live " +
                        "WHERE (cluster_name = '" + clusterCode + "') " +
                        "GROUP BY cluster_name, host";
        // AND time >= now() - 6h
        Query query = new Query(queryRequestReceive, influxDBProperties.getDatabase());
        QueryResult queryResult = influxDBTemplate.query(query);
        return resultToMap(queryResult);
    }

    /**
     * 查询JVM 线程峰值
     *
     * @param clusterCode
     * @return
     */
    public Map<String, Object> queryJvmThreadsPeak(String clusterCode) {
        String queryRequestReceive =
                "SELECT cluster_name, env, host,value " +
                        "FROM jvm_threads_peak " +
                        "WHERE (cluster_name = '" + clusterCode + "') " +
                        "GROUP BY cluster_name, host";
        // AND time >= now() - 6h
        Query query = new Query(queryRequestReceive, influxDBProperties.getDatabase());
        QueryResult queryResult = influxDBTemplate.query(query);
        return resultToMap(queryResult);
    }

    /**
     * 查询JVM线程状态
     *
     * @param clusterCode
     * @return
     */
    public Map<String, Object> queryJvmThreadsStates(String clusterCode) {
        String queryRequestReceive =
                "SELECT cluster_name, env, host,value " +
                        "FROM jvm_threads_states " +
                        "WHERE (cluster_name = '" + clusterCode + "') " +
                        "GROUP BY cluster_name, host";
        // AND time >= now() - 6h
        Query query = new Query(queryRequestReceive, influxDBProperties.getDatabase());
        QueryResult queryResult = influxDBTemplate.query(query);
        return resultToMap(queryResult);
    }

    /**
     * 查询监控度量大小
     *
     * @param clusterCode
     * @return
     */
    public Map<String, Object> queryMicrometerMetersSize(String clusterCode) {
        String queryRequestReceive =
                "SELECT cluster_name, env, host,value " +
                        "FROM micrometer_meters_size " +
                        "WHERE (cluster_name = '" + clusterCode + "') " +
                        "GROUP BY cluster_name, host";
        // AND time >= now() - 6h
        Query query = new Query(queryRequestReceive, influxDBProperties.getDatabase());
        QueryResult queryResult = influxDBTemplate.query(query);
        return resultToMap(queryResult);
    }

    /**
     * 查询处理器CPU已使用数量
     *
     * @param clusterCode
     * @return
     */
    public Map<String, Object> queryProcessCpuUsage(String clusterCode) {
        String queryRequestReceive =
                "SELECT cluster_name, env, host,value " +
                        "FROM process_cpu_usage " +
                        "WHERE (cluster_name = '" + clusterCode + "') " +
                        "GROUP BY cluster_name, host";
        // AND time >= now() - 6h
        Query query = new Query(queryRequestReceive, influxDBProperties.getDatabase());
        QueryResult queryResult = influxDBTemplate.query(query);
        return resultToMap(queryResult);
    }

    /**
     * 查询处理开始时间
     *
     * @param clusterCode
     * @return
     */
    public Map<String, Object> queryProcessStartTime(String clusterCode) {
        String queryRequestReceive =
                "SELECT cluster_name, env, host,value " +
                        "FROM process_start_time " +
                        "WHERE (cluster_name = '" + clusterCode + "') " +
                        "GROUP BY cluster_name, host";
        // AND time >= now() - 6h
        Query query = new Query(queryRequestReceive, influxDBProperties.getDatabase());
        QueryResult queryResult = influxDBTemplate.query(query);
        return resultToMap(queryResult);
    }

    /**
     * 查询处理更新时间
     *
     * @param clusterCode
     * @return
     */
    public Map<String, Object> queryProcessUptime(String clusterCode) {
        String queryRequestReceive =
                "SELECT cluster_name, env, host,value " +
                        "FROM process_uptime " +
                        "WHERE (cluster_name = '" + clusterCode + "') " +
                        "GROUP BY cluster_name, host";
        // AND time >= now() - 6h
        Query query = new Query(queryRequestReceive, influxDBProperties.getDatabase());
        QueryResult queryResult = influxDBTemplate.query(query);
        return resultToMap(queryResult);
    }

    /**
     * 查询请求已完成数量
     *
     * @param clusterCode
     * @return
     */
    public Map<String, Object> queryRequestFinished(String clusterCode) {
        String queryRequestReceive =
                "SELECT cluster_name, env, host,value " +
                        "FROM request_finished " +
                        "WHERE (cluster_name = '" + clusterCode + "') " +
                        "GROUP BY cluster_name, host";
        // AND time >= now() - 6h
        Query query = new Query(queryRequestReceive, influxDBProperties.getDatabase());
        QueryResult queryResult = influxDBTemplate.query(query);
        return resultToMap(queryResult);
    }

    /**
     * 请求完成直方图
     *
     * @param clusterCode
     * @return
     */
    public Map<String, Object> queryRequestFinishedHistogram(String clusterCode) {
        String queryRequestReceive =
                "SELECT cluster_name, env, host,value " +
                        "FROM request_finished_histogram " +
                        "WHERE (cluster_name = '" + clusterCode + "') " +
                        "GROUP BY cluster_name, host";
        // AND time >= now() - 6h
        Query query = new Query(queryRequestReceive, influxDBProperties.getDatabase());
        QueryResult queryResult = influxDBTemplate.query(query);
        return resultToMap(queryResult);
    }

    /**
     * 查询请求已完成百分比
     *
     * @param clusterCode
     * @return
     */
    public Map<String, Object> queryRequestFinishedPercentile(String clusterCode) {
        String queryRequestReceive =
                "SELECT cluster_name, env, host,value " +
                        "FROM request_finished_percentile " +
                        "WHERE (cluster_name = '" + clusterCode + "') " +
                        "GROUP BY cluster_name, host";
        // AND time >= now() - 6h
        Query query = new Query(queryRequestReceive, influxDBProperties.getDatabase());
        QueryResult queryResult = influxDBTemplate.query(query);
        return resultToMap(queryResult);
    }


    /**
     * 系统负载均值
     *
     * @param clusterCode
     * @return
     */
    public Map<String, Object> querySystemLoadAverage(String clusterCode) {
        String queryRequestReceive =
                "SELECT cluster_name, env, host,value " +
                        "FROM system_load_average_1m " +
                        "WHERE (cluster_name = '" + clusterCode + "') " +
                        "GROUP BY cluster_name, host";
        // AND time >= now() - 6h
        Query query = new Query(queryRequestReceive, influxDBProperties.getDatabase());
        QueryResult queryResult = influxDBTemplate.query(query);
        return resultToMap(queryResult);
    }

    public Map<String, Object> queryJVMHeapMemoryMax(String clusterName, String host, String area) {
        String queryRequestReceive = "SELECT * FROM jvm_memory_max" +
                "  where cluster_name='" + clusterName + "' and host='" + host + "' and area='" + area + "' ";
        Query query = new Query(queryRequestReceive, influxDBProperties.getDatabase());
        QueryResult queryResult = influxDBTemplate.query(query);
        return resultToJVMHeap(queryResult);
    }
    public Map<String, Object> queryJVMHeapMemoryUsed(String clusterName, String host, String area) {
        String queryRequestReceive = "SELECT * FROM jvm_memory_used" +
                "  where cluster_name='" + clusterName + "' and host='" + host + "' and area='" + area + "' ";
        Query query = new Query(queryRequestReceive, influxDBProperties.getDatabase());
        QueryResult queryResult = influxDBTemplate.query(query);
        return resultToJVMHeap(queryResult);
    }

    public Map<String, Object> queryJVMHeapMemoryCommitted(String clusterName, String host, String area) {
        String queryRequestReceive = "SELECT * FROM jvm_memory_committed" +
                "  where cluster_name='" + clusterName + "' and host='" + host + "' and area='" + area + "' ";
        Query query = new Query(queryRequestReceive, influxDBProperties.getDatabase());
        QueryResult queryResult = influxDBTemplate.query(query);
        return resultToJVMHeap(queryResult);
    }

    public Map<String, Object> resultToJVMHeap(QueryResult queryResult) {
        HashMap<String, Object> map = new HashMap<>();
        List<QueryResult.Result> results = queryResult.getResults();
        // 多个sql用分号隔开，因本次查询只有一个sql，所以取第一个就行
        QueryResult.Result result = results.get(0);
        //查询出来的结果
        List<QueryResult.Series> seriesList = result.getSeries();
        Map<String, Double> totalValues = new TreeMap<>();
        if (CollectionUtils.isNotEmpty(seriesList)) {
            // key:当前时间戳    value:当前调用总数量
            Map<String, Double> totalCount = new TreeMap<>();
            List<KeyValueCO> totalList = new ArrayList<>();
            List<KeyValueCO> ipPorts = new ArrayList<>();
            for (QueryResult.Series series : seriesList) {
                //遍历数据
                List<String> columns = series.getColumns();
                int timeIndex = columns.indexOf("time");
                int valueIndex = columns.indexOf("value");
                int hostIndex = columns.indexOf("host");
                series.getValues().forEach(testData -> {
                    Object time = testData.get(timeIndex);
                    String ip = testData.get(hostIndex)+"";
                    Double value = (Double) testData.get(valueIndex);
                    if (value != 0) {
                        value = value / 1024 / 1024 / 1024;
                        BigDecimal b   =   new   BigDecimal(value);
                        value   =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
                    }
                    String resultTime = parseUTCDate(time);
                    if (totalValues.get(resultTime) == null) {
                        totalValues.put(resultTime, value);
                    } else {
                        totalValues.put(resultTime, totalValues.get(resultTime) + value);
                    }
                    Double totalResult = totalCount.get(resultTime) == null ? value : totalCount.get(resultTime) + value;
                    totalCount.put(resultTime, totalResult);
                    KeyValueCO keyValueCO = new KeyValueCO();
                    keyValueCO.setKey(resultTime);
                    keyValueCO.setValue(value);
                    ipPorts.add(keyValueCO);
                    map.put(ip, ipPorts);
                });
            }
            //totalMap转totalList
            for (Map.Entry<String, Double> entry : totalCount.entrySet()) {
                KeyValueCO keyValueCO1 = new KeyValueCO();
                keyValueCO1.setKey(entry.getKey());
                keyValueCO1.setValue(entry.getValue());
                totalList.add(keyValueCO1);
            }
            map.put("total", totalList);
            Map<String, Double> doubleHashMap = new HashMap<>();
            List<KeyValueCO> list = new ArrayList<>();

            for (Map.Entry<String, Double> entry : totalValues.entrySet()) {
                Double value = entry.getValue();
                String key = entry.getKey();
                BigDecimal b   =   new   BigDecimal(value);
                value   =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
                doubleHashMap.put(key, value);
                KeyValueCO keyValueCO = new KeyValueCO();
                keyValueCO.setKey(key);
                keyValueCO.setValue(value);
                list.add(keyValueCO);
            }
            map.put("totalValues", list);
        }
        return map;
    }
    public Map<String, Object> queryDiskInfo(String clusterName, String ip) {
        Map<String, Object> map = new HashMap<>();
        String diskFreeSQL = "SELECT value FROM disk_free WHERE " +
                "(cluster_name = '" + clusterName + "' AND host = '" + ip + "')   order by time desc limit 1";
        Query diskFreeSQLQuery = new Query(diskFreeSQL, influxDBProperties.getDatabase());
        QueryResult queryResult = influxDBTemplate.query(diskFreeSQLQuery);
        Map<String, Object> diskFreeResult = resultToValueMap(queryResult);

        String diskTotalSQL = "SELECT value FROM disk_total WHERE " +
                "(cluster_name = '" + clusterName + "' AND host = '" + ip + "')   order by time desc limit 1";
        Query diskTotalSQLQuery = new Query(diskTotalSQL, influxDBProperties.getDatabase());
        QueryResult diskTotalQueryResult = influxDBTemplate.query(diskTotalSQLQuery);
        Map<String, Object> diskTotalResult = resultToValueMap(diskTotalQueryResult);

        map.put("diskFreeResult", diskFreeResult);
        map.put("diskTotalResult", diskTotalResult);
        return map;
    }


    public Map<String, Object> resultToJVMMap(QueryResult queryResult) {
        HashMap<String, Object> map = new HashMap<>();
        List<QueryResult.Result> results = queryResult.getResults();
        // 多个sql用分号隔开，因本次查询只有一个sql，所以取第一个就行
        QueryResult.Result result = results.get(0);
        //查询出来的结果
        List<QueryResult.Series> seriesList = result.getSeries();
        if (CollectionUtils.isNotEmpty(seriesList)) {
            // key:当前时间戳    value:当前调用总数量
            Map<String, Double> totalCount = new TreeMap<>();
            List<KeyValueCO> totalList = new ArrayList<>();
            List<KeyValueCO> ipPorts = new ArrayList<>();
            for (QueryResult.Series series : seriesList) {
                //遍历数据
                Map<String, String> tags = series.getTags();
                List<String> columns = series.getColumns();
                int timeIndex = columns.indexOf("time");
                int valueIndex = columns.indexOf("value");
                int hostIndex = columns.indexOf("host");
                series.getValues().forEach(testData -> {
                    Object time = testData.get(timeIndex);
                    String ip = testData.get(hostIndex)+"";
                    Double value = (Double) testData.get(valueIndex);
                    if (value != 0) {
                        value = value / 1024 / 1024 ;
                        BigDecimal b   =   new   BigDecimal(value);
                        value   =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
                    }
                    String resultTime = parseUTCDate(time);
                    Double totalResult = totalCount.get(resultTime) == null ? value : totalCount.get(resultTime) + value;
                    totalCount.put(resultTime, totalResult);
                    KeyValueCO keyValueCO = new KeyValueCO();
                    keyValueCO.setKey(resultTime);
                    keyValueCO.setValue(value);
                    ipPorts.add(keyValueCO);
                    map.put(ip, ipPorts);
                });
            }
            //totalMap转totalList
            for (Map.Entry<String, Double> entry : totalCount.entrySet()) {
                KeyValueCO keyValueCO1 = new KeyValueCO();
                keyValueCO1.setKey(entry.getKey());
                keyValueCO1.setValue(entry.getValue());
                totalList.add(keyValueCO1);
            }
            map.put("total", totalList);
        }
        return map;
    }


    public Map<String, Object> resultToJVMGBMap(QueryResult queryResult) {
        HashMap<String, Object> map = new HashMap<>();
        List<QueryResult.Result> results = queryResult.getResults();
        // 多个sql用分号隔开，因本次查询只有一个sql，所以取第一个就行
        QueryResult.Result result = results.get(0);
        //查询出来的结果
        List<QueryResult.Series> seriesList = result.getSeries();
        if (CollectionUtils.isNotEmpty(seriesList)) {
            // key:当前时间戳    value:当前调用总数量
            Map<String, Double> totalCount = new TreeMap<>();
            List<KeyValueCO> totalList = new ArrayList<>();
            List<KeyValueCO> ipPorts = new ArrayList<>();
            for (QueryResult.Series series : seriesList) {
                //遍历数据
                Map<String, String> tags = series.getTags();
                List<String> columns = series.getColumns();
                int timeIndex = columns.indexOf("time");
                int valueIndex = columns.indexOf("value");
                int hostIndex = columns.indexOf("host");
                series.getValues().forEach(testData -> {
                    Object time = testData.get(timeIndex);
                    String ip = testData.get(hostIndex)+"";
                    Double value = (Double) testData.get(valueIndex);
                    if (value != 0) {
                        value = value / 1024 / 1024 / 1024;
                        BigDecimal b   =   new   BigDecimal(value);
                        value   =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
                    }
                    String resultTime = parseUTCDate(time);
                    Double totalResult = totalCount.get(resultTime) == null ? value : totalCount.get(resultTime) + value;
                    totalCount.put(resultTime, totalResult);
                    KeyValueCO keyValueCO = new KeyValueCO();
                    keyValueCO.setKey(resultTime);
                    keyValueCO.setValue(value);
                    ipPorts.add(keyValueCO);
                    map.put(ip, ipPorts);
                });
            }
            //totalMap转totalList
            for (Map.Entry<String, Double> entry : totalCount.entrySet()) {
                KeyValueCO keyValueCO1 = new KeyValueCO();
                keyValueCO1.setKey(entry.getKey());
                keyValueCO1.setValue(entry.getValue());
                totalList.add(keyValueCO1);
            }
            map.put("total", totalList);
        }
        return map;
    }

    public Map<String, Object> resultToValueMap(QueryResult queryResult) {
        HashMap<String, Object> map = new HashMap<>();
        List<QueryResult.Result> results = queryResult.getResults();
        // 多个sql用分号隔开，因本次查询只有一个sql，所以取第一个就行
        QueryResult.Result result = results.get(0);
        //查询出来的结果
        List<QueryResult.Series> seriesList = result.getSeries();
        if (CollectionUtils.isNotEmpty(seriesList)) {
            // key:当前时间戳    value:当前调用总数量
            Map<String, Double> totalCount = new TreeMap<>();
            List<Object> list = seriesList.get(0).getValues().get(0);
            Object time = list.get(0);
            String resultTime = parseUTCDate(time);
            double value = (double) list.get(1);
            value = value / 1024 / 1024 / 1024;
            BigDecimal b   =   new   BigDecimal(value);
            value   =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
            map.put("time", resultTime);
            map.put("value", value);
        }
        return map;
    }


    public Map<String, Object> resultToMap(QueryResult queryResult) {
        HashMap<String, Object> map = new HashMap<>();
        List<QueryResult.Result> results = queryResult.getResults();
        // 多个sql用分号隔开，因本次查询只有一个sql，所以取第一个就行
        QueryResult.Result result = results.get(0);
        //查询出来的结果
        List<QueryResult.Series> seriesList = result.getSeries();
        if (CollectionUtils.isNotEmpty(seriesList)) {
            // key:当前时间戳    value:当前调用总数量
            Map<String, Double> totalCount = new TreeMap<>();
            List<KeyValueCO> totalList = new ArrayList<>();
            List<KeyValueCO> ipPorts = new ArrayList<>();
            for (QueryResult.Series series : seriesList) {
                //遍历数据
                Map<String, String> tags = series.getTags();
                List<String> columns = series.getColumns();
                int timeIndex = columns.indexOf("time");
                int valueIndex = columns.indexOf("value");
                int hostIndex = columns.indexOf("host");
                series.getValues().forEach(testData -> {
                    Object time = testData.get(timeIndex);
                    String ip = testData.get(hostIndex)+"";
                    Double value = (Double) testData.get(valueIndex);
                    String resultTime = parseUTCDate(time);
                    Double totalResult = totalCount.get(resultTime) == null ? value : totalCount.get(resultTime) + value;
                    totalCount.put(resultTime, totalResult);
                    KeyValueCO keyValueCO = new KeyValueCO();
                    keyValueCO.setKey(resultTime);
                    keyValueCO.setValue(value);
                    ipPorts.add(keyValueCO);
                    map.put(ip, ipPorts);
                });
            }
            //totalMap转totalList
            for (Map.Entry<String, Double> entry : totalCount.entrySet()) {
                KeyValueCO keyValueCO1 = new KeyValueCO();
                keyValueCO1.setKey(entry.getKey());
                keyValueCO1.setValue(entry.getValue());
                totalList.add(keyValueCO1);
            }
            map.put("total", totalList);
        }
        return map;
    }

    private String parseUTCDate(Object utcDate) {
        String result = "";
        String utcDateString = utcDate.toString();
        String format = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
        String format1 = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        SimpleDateFormat sdf1 = new SimpleDateFormat(format1);
        try {
            Date date = sdf.parse(utcDateString);
            result = sdf1.format(date);
        } catch (ParseException e) {
            log.error("parseUTCDate error:{},参数:{}", e, utcDate);
        }
        return result;
    }
}
