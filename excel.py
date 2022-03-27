import pandas as pd

new_list = [["first", "second"], ["third", "four"], ["five", "six"]]
df = pd.DataFrame(new_list)
writer = pd.ExcelWriter('test.xlsx', engine='xlsxwriter')
df.to_excel(writer, sheet_name='welcome', index=False)
writer.save()
