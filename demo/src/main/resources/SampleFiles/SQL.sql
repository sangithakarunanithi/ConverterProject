select name, path ,value, count(*) as cnt from aidaptechdtrainer.node
where value is not null
 group by name, path ,value
 having cnt > 1;

select parent_id from aidaptechdtrainer.node  where path = '/teamworks/bpd/BusinessProcessDiagram/pool/lane/flowObject/component/bpmnTaskType' and value = '1';
select * from aidaptechdtrainer.node  where name = 'bpmnTaskType';-- = '/teamworks/bpd/BusinessProcessDiagram/pool/lane/flowObject/@componentType' and value = 'Activity';

SELECT *
FROM aidaptechdtrainer.node
WHERE id IN(
WITH RECURSIVE cte AS (
    SELECT id, parent_id
    FROM aidaptechdtrainer.node
    WHERE id in ( 9890,10064,10229,10325,10368,10834,10901)
    
    UNION ALL
    
    SELECT t.id, t.parent_id
    FROM aidaptechdtrainer.node t
    INNER JOIN cte ON t.parent_id = cte.id
)
SELECT id
FROM cte
WHERE id not in ( 9890,10064,10229,10325,10368,10834,10901));


SELECT aidaptechdtrainer.node.name,aidaptechdtrainer.node.path,aidaptechdtrainer.node.value,count(*) as cnt
FROM aidaptechdtrainer.node
JOIN (
    WITH RECURSIVE cte AS (
        SELECT id, parent_id
        FROM aidaptechdtrainer.node
        WHERE id IN ( 9901,
10075,
10240,
10845) -- Pass the multiple input IDs here
        
        UNION ALL
        
        SELECT t.id, t.parent_id
        FROM aidaptechdtrainer.node t
        INNER JOIN cte ON t.parent_id = cte.id
    )
    SELECT id
    FROM cte
    WHERE id NOT IN ( 9901,
10075,
10240,
10845) -- Exclude the input IDs from the result
) AS cte ON aidaptechdtrainer.node.id = cte.id
where aidaptechdtrainer.node.value is not null
group by aidaptechdtrainer.node.name, aidaptechdtrainer.node.path,aidaptechdtrainer.node.value
having cnt > 1
ORDER BY aidaptechdtrainer.node.name;

--having cnt > 1 and cnt <> 7