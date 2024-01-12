-- 코드를 입력하세요
select concat('/home/grep/src/', b.board_id, '/',
              f.file_id, f.file_name, f.file_ext) as 'FILE_PATH'
from used_goods_board as b
join used_goods_file as f
  on b.board_id = f.board_id
where b.views = (SELECT views
                from used_goods_board
                order by views desc limit 1)
order by f.file_id desc
;

# select *
# from used_goods_board
# order by views desc;